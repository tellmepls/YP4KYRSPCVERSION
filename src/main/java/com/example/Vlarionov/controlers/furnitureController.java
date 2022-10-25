package com.example.Vlarionov.controlers;

import com.example.Vlarionov.Models.*;
import com.example.Vlarionov.Repositories.furnitureRepository;
import com.example.Vlarionov.Repositories.dopInformationRepository;
import com.example.Vlarionov.Repositories.premiseRepository;
import com.example.Vlarionov.Repositories.shopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
public class furnitureController {
    @Autowired
    furnitureRepository FurnitureRepository;
    @Autowired
    dopInformationRepository DopInforamtionRep;
    @Autowired
    premiseRepository premiseRepository;
    @Autowired
    shopRepository shopRepository;


    @GetMapping("/furniture")
    public String FFurniture(Model model) {

        Iterable<furniture> listFurniture = FurnitureRepository.findAll();
        model.addAttribute(("list_furniture"), listFurniture);


        return ("furniture/index");
    }

    @GetMapping("furniture/add")
    public String furnitureAddView(furniture Furniture, Model model) {

        Iterable<dopInformation> Dopinfos = DopInforamtionRep.findAll();
        ArrayList<dopInformation> DopinfosArrayList = new ArrayList<>();

        for(dopInformation di: Dopinfos) {
            if(di.getOwner() == null) {
                DopinfosArrayList.add(di);
            }
        }
        Iterable<Premise> premise = premiseRepository.findAll();
        model.addAttribute("dopinformation", DopinfosArrayList);
        model.addAttribute("premise", premise);
        return ("furniture/furnitureADD");
    }

    @PostMapping("furniture/add")
    public String furnitureAdd(@Valid furniture Furniture,
                              BindingResult result,
                                @RequestParam String design,
                               @RequestParam String meaning) {
        if(result.hasErrors())
            return ("furniture/furnitureADD");
        dopInformation dp = DopInforamtionRep.findBydesign(design);
        Premise premise = premiseRepository.findBymeaning(meaning);
        Furniture.setDopInformation(dp);
        Furniture.setPremise(premise);
        FurnitureRepository.save(Furniture);
        return ("redirect:/furniture");
    }

    @GetMapping("furniture/filterACCU")
    public String furnitureFilterACCU(Model model,
                                     @RequestParam(name = "search") String type) {

        List<furniture> employeeList = FurnitureRepository.findByType(type);
        model.addAttribute("searchRes", employeeList);
        return ("furniture/furnitureFilter");
    }

    @GetMapping("furniture/filter")
    public String furnitureFilter(Model model,
                                      @RequestParam(name = "search") String type) {

        List<furniture> employeeList = FurnitureRepository.findByType(type);
        model.addAttribute("searchRes", employeeList);
        return ("furniture/furnitureFilter");
    }
   @GetMapping("/furniture/details/{id}")
      public String EmployeeDetails(Model model,
                                @PathVariable long id) {

       Optional<furniture> car = FurnitureRepository.findById(id);
       ArrayList<furniture> result = new ArrayList<>();

       car.ifPresent(result::add);
       model.addAttribute("furniture", result);
       return ("/furniture/furnitureDetails");
   }

    @GetMapping("furniture/delete/{id}")
    public String furnitureDelete(@PathVariable long id) {

        FurnitureRepository.deleteById(id);
        return("redirect:/furniture");
    }

    @GetMapping("furniture/furnitureEdit/{id}")
    public String furnitureEdit(Model model,
                               @PathVariable long id) {

        furniture emp = FurnitureRepository.findById(id).orElseThrow();
        model.addAttribute("furniture", emp);
        return("furniture/furnitureEdit");
    }

    @PostMapping("furniture/furnitureEdit/{id}")
    public String furnitureEdit(@Valid furniture furniture,
                               BindingResult bindingResult
    ){
        if(bindingResult.hasErrors())
            return("furniture/furnitureEdit");

        FurnitureRepository.save(furniture);

        return("redirect:/furniture/details/" + furniture.getId());
    }
    @GetMapping("/furniture/dopinformationADD")
    public String DopInfoAdd(Model model)
    {

        return ("/furniture/dopinformationADD");
    }

    @PostMapping("/furniture/dopinformationADD")
    public String DopInfoAdd(
            @RequestParam String design,
            @RequestParam String numberOfLockers
    ) {
        dopInformation DI = new dopInformation(design,numberOfLockers);
        DopInforamtionRep.save(DI);
        return ("redirect:/furniture");
    }
    @GetMapping("/furniture/PremiseADD")
    public String PremiseADD(Model model)
    {
        return ("/furniture/PremiseADD");
    }

    @PostMapping("/furniture/PremiseADD")
    public String PremiseADD(
            @RequestParam String meaning,
            @RequestParam String square
    ) {
        Premise premise = new Premise(meaning,square);
        premiseRepository.save(premise);
        return ("redirect:/furniture");
    }


    @GetMapping("/furniture/shopADD")
    public String furnitureADD(Model model)
    {
        return ("/furniture/shopADD");
    }
    @GetMapping("/furniture/shops")
    public String shops(Model model)
    {
        Iterable<shop> listShop = shopRepository.findAll();
        model.addAttribute("list_shop",listShop);
        return ("/furniture/shops");
    }


    @PostMapping("/furniture/shopADD")
    public String furnitureADD(
            @RequestParam String Address,
            @RequestParam String typeSeparation
    ) {
        shop shop = new shop(Address,typeSeparation);
        shopRepository.save(shop);
        return ("redirect:/furniture");
    }

    @GetMapping("/furniture/shop_furnitureADD")
    public String shop_furnitureAdd(Model model)
    {
        Iterable<furniture> furnituress = FurnitureRepository.findAll();
        model.addAttribute("furnitures", furnituress);
        Iterable<shop> shopp = shopRepository.findAll();
        model.addAttribute("shops", shopp);

        return ("/furniture/shop_furnitureADD");
    }

    @PostMapping("/furniture/shop_furnitureADD")
    public String shop_furnitureAddD(
            @RequestParam String shop,
            @RequestParam String furniture
    ) {
        furniture f = FurnitureRepository.findByType(Arrays.stream((furniture.split(" "))).toList().get(0)).get(0);
        shop shops = shopRepository.findByaddress(shop);
        List<shop> ss =f.getShops();
        ss.add(shops);
        f.setShops(ss);
        FurnitureRepository.save(f);
        return ("redirect:/furniture");
    }
    @GetMapping("/furniture/admin")
    public String AdminPanel(Model model) {

        Iterable<furniture> listEmployee = FurnitureRepository.findAll();
        model.addAttribute(("list_furniture"), listEmployee);
        return ("furniture/adminPanel");
    }

    @GetMapping("/furniture/admin/Edit-role/{id}")
    public String EmployeeRoleEdit(Model model,
                                   @PathVariable long id) {

        furniture f = FurnitureRepository.findById(id).orElseThrow();
        model.addAttribute("furniture", f);
        model.addAttribute("listRoles", Role.values());
        return("/furniture/Edit-role");
    }

    @PostMapping("/furniture/admin/Edit-role/{id}")
    public String EmployeeRoleEdit(@PathVariable long id,
                                   @RequestParam String[] roles) {

        furniture f = FurnitureRepository.findById(id).orElseThrow();
        f.getRoles().clear();

        for(String role: roles){
            f.getRoles().add(Role.valueOf(role));
        }

        FurnitureRepository.save(f);

        return("redirect:/furniture/admin");
    }
}
