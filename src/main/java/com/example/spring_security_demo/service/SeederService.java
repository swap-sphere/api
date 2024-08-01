package com.example.spring_security_demo.service;

import com.example.spring_security_demo.dao.*;
import com.example.spring_security_demo.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SeederService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private FilterRepository filterRepository;

    @Autowired
    private FilterOptionRepository filterOptionRepository;

    @Autowired
    private FilterValuesRepository filterValuesRepository;

    @Autowired
    private InventoryRepository inventoryRepository;

    public void seed() {
        Category electronics = new Category("Electronics", null);
        categoryRepository.save(electronics);

        Category sports = new Category("Sports", null);
        categoryRepository.save(sports);

        Category laptop = new Category("Laptop", electronics);
        categoryRepository.save(laptop);

        Filter brandLaptop = new Filter("Brand", laptop);
        FilterOption apple = new FilterOption("Apple", brandLaptop);
        FilterOption lenovo = new FilterOption("Lenovo", brandLaptop);
        FilterOption dell = new FilterOption("Dell", brandLaptop);

        filterOptionRepository.save(apple);
        filterOptionRepository.save(lenovo);
        filterOptionRepository.save(dell);



        Filter ramLaptop = new Filter("RAM", laptop);
        FilterOption ram16GB = new FilterOption("16 GB", ramLaptop);
        FilterOption ram8GB = new FilterOption("8 GB", ramLaptop);
        FilterOption ram4GB = new FilterOption("4 GB", ramLaptop);

        filterOptionRepository.save(ram16GB);
        filterOptionRepository.save(ram8GB);
        filterOptionRepository.save(ram4GB);

        Filter screenSize = new Filter("Screen Size", laptop);
        FilterOption inch16 = new FilterOption("16 inch", screenSize);
        FilterOption inch14 = new FilterOption("14 inch", screenSize);
        FilterOption inch13 = new FilterOption("13 inch", screenSize);

        filterOptionRepository.save(inch16);
        filterOptionRepository.save(inch14);
        filterOptionRepository.save(inch13);



        Filter ssd = new Filter("SSD", laptop);
        FilterOption ssd512 = new FilterOption("512", ssd);
        FilterOption ssd256 = new FilterOption("256", ssd);
        FilterOption ssd128 = new FilterOption("128", ssd);

        filterOptionRepository.save(ssd512);
        filterOptionRepository.save(ssd256);
        filterOptionRepository.save(ssd128);



        filterRepository.save(ramLaptop);
        filterRepository.save(screenSize);
        filterRepository.save(ssd);
        filterRepository.save(brandLaptop);

        Inventory macbook16 = new Inventory("macbook16","M2 chip inside", 100, 1500, 0.2f, laptop);
        inventoryRepository.save(macbook16);

        FilterValues macbook16brand = new FilterValues(apple,macbook16);
        filterValuesRepository.save(macbook16brand);

        FilterValues macbook16ram = new FilterValues(ram8GB,macbook16);
        filterValuesRepository.save(macbook16ram);

        FilterValues macbook16ssd = new FilterValues(ssd256,macbook16);
        filterValuesRepository.save(macbook16ssd);

        FilterValues macbook16screenSize = new FilterValues(inch14,macbook16);
        filterValuesRepository.save(macbook16screenSize);


        Inventory lenovoIdealPad = new Inventory("lenovo IdealPad","Intel Core i9 7th Gen chip inside", 90, 1000, 0.3f, laptop);
        inventoryRepository.save(lenovoIdealPad);

        FilterValues idealPadbrand = new FilterValues(lenovo,lenovoIdealPad);
        filterValuesRepository.save(idealPadbrand);

        FilterValues idealPadram = new FilterValues(ram16GB,lenovoIdealPad);
        filterValuesRepository.save(idealPadram);

        FilterValues idealPadssd = new FilterValues(ssd512,lenovoIdealPad);
        filterValuesRepository.save(idealPadssd);

        FilterValues idealPadscreenSize = new FilterValues(inch13,lenovoIdealPad);
        filterValuesRepository.save(idealPadscreenSize);


        Inventory dellInspiron = new Inventory("Dell Inspiron","Intel Core i9 9th Gen chip inside", 80, 900, 0.1f, laptop);
        inventoryRepository.save(dellInspiron);

        FilterValues inspironBrand = new FilterValues(dell,dellInspiron);
        filterValuesRepository.save(inspironBrand);

        FilterValues inspironRam = new FilterValues(ram4GB,dellInspiron);
        filterValuesRepository.save(inspironRam);

        FilterValues inspironSsd = new FilterValues(ssd128,dellInspiron);
        filterValuesRepository.save(inspironSsd);

        FilterValues inspironScreenSize = new FilterValues(inch16,dellInspiron);
        filterValuesRepository.save(inspironScreenSize);



        Category mobile = new Category("Mobile", electronics);
        categoryRepository.save(mobile);

        Filter ramPhone = new Filter("RAM", mobile);
        FilterOption ramPhone8GB = new FilterOption("8 GB", ramPhone);
        FilterOption ramPhone12GB = new FilterOption("12 GB", ramPhone);
        FilterOption ramPhone16GB = new FilterOption("16 GB", ramPhone);

        filterOptionRepository.save(ramPhone8GB);
        filterOptionRepository.save(ramPhone12GB);
        filterOptionRepository.save(ramPhone16GB);

        Filter storage = new Filter("ROM", mobile);
        FilterOption rom128GB = new FilterOption("128 GB", storage);
        FilterOption rom256GB = new FilterOption("256 GB", storage);
        FilterOption rom512GB = new FilterOption("512 GB", storage);

        filterOptionRepository.save(rom128GB);
        filterOptionRepository.save(rom256GB);
        filterOptionRepository.save(rom512GB);

        Filter brandMobile = new Filter("Brand", mobile);
        FilterOption Apple = new FilterOption("Apple", brandMobile);
        FilterOption samsung = new FilterOption("Samsung", brandMobile);
        FilterOption onePlus = new FilterOption("one Plus", brandMobile);

        filterOptionRepository.save(Apple);
        filterOptionRepository.save(samsung);
        filterOptionRepository.save(onePlus);


        filterRepository.save(ramPhone);
        filterRepository.save(storage);
        filterRepository.save(brandMobile);


        Inventory iPhone15 = new Inventory("IPhone 15","All new IPhone 15 with Inbuild AI Assistance", 50, 1000, 0.1f, mobile);
        inventoryRepository.save(iPhone15);

        FilterValues phoneBrand = new FilterValues(Apple,iPhone15);
        filterValuesRepository.save(phoneBrand);

        FilterValues iPhoneRam = new FilterValues(ramPhone16GB,iPhone15);
        filterValuesRepository.save(iPhoneRam);

        FilterValues iPhoneRom = new FilterValues(rom512GB,iPhone15);
        filterValuesRepository.save(iPhoneRam);



        Inventory samsungS24 = new Inventory("Samsung S24","Samsung S24 with 120X Zoom", 60, 1200, 0.1f, mobile);
        inventoryRepository.save(samsungS24);

        FilterValues s24Brand = new FilterValues(samsung,samsungS24);
        filterValuesRepository.save(s24Brand);

        FilterValues s24Ram = new FilterValues(ramPhone12GB,samsungS24);
        filterValuesRepository.save(s24Ram);

        FilterValues s24Rom = new FilterValues(rom512GB,samsungS24);
        filterValuesRepository.save(s24Rom);


        Inventory onePlus12 = new Inventory("One Plus 12","One Plus 12 with 108MP camera ", 70, 800, 0.1f, mobile);
        inventoryRepository.save(onePlus12);

        FilterValues onePlus12Brand = new FilterValues(onePlus,onePlus12);
        filterValuesRepository.save(onePlus12Brand);

        FilterValues onePlusRam = new FilterValues(ramPhone8GB,onePlus12);
        filterValuesRepository.save(onePlusRam);

        FilterValues onePlusRom = new FilterValues(rom128GB,onePlus12);
        filterValuesRepository.save(onePlusRom);



        Category shoes = new Category("Shoes", sports);
        categoryRepository.save(shoes);

        Filter brandShoe = new Filter("Brand", shoes);
        Filter sizeShoe = new Filter("Size", shoes);
        Filter typeShoe = new Filter("Type", shoes);

        filterRepository.save(brandShoe);
        filterRepository.save(sizeShoe);
        filterRepository.save(typeShoe);

    }
}
