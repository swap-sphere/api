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
        System.out.println("heear=====;;;");


        // Categories
        Category electronics = new Category("Electronics", null);
        Category laptop = new Category("Laptop", electronics);
        Category mobile = new Category("Mobile", electronics);

        categoryRepository.save(electronics);
        categoryRepository.save(laptop);
        categoryRepository.save(mobile);

        // Filters
        Filter screenSizeLaptop = new Filter( "Screen size", laptop);
        Filter ramLaptop = new Filter("RAM", laptop);
        Filter colorLaptop = new Filter("Color", laptop);

        Filter screenSizeMobile = new Filter("Screen size", mobile);
        Filter ramMobile = new Filter("RAM", mobile);

        filterRepository.save(screenSizeLaptop);
        filterRepository.save(ramLaptop);
        filterRepository.save(colorLaptop);
        filterRepository.save(screenSizeMobile);
        filterRepository.save(ramMobile);

        // Filter Options
        // Laptop Filter Options
        FilterOption laptop16Inch = new FilterOption("16 inch", screenSizeLaptop);
        FilterOption laptop14Inch = new FilterOption("14 inch", screenSizeLaptop);

        FilterOption ram16GBLaptop = new FilterOption( "16 GB", ramLaptop);
        FilterOption ram8GBLaptop = new FilterOption("8 GB", ramLaptop);

        FilterOption spaceGray = new FilterOption("Space Gray", colorLaptop);
        FilterOption white = new FilterOption("White", colorLaptop);
        FilterOption silver = new FilterOption("Silver", colorLaptop);

        filterOptionRepository.save(laptop16Inch);
        filterOptionRepository.save(laptop14Inch);
        filterOptionRepository.save(ram16GBLaptop);
        filterOptionRepository.save(ram8GBLaptop);
        filterOptionRepository.save(spaceGray);
        filterOptionRepository.save(white);
        filterOptionRepository.save(silver);

        // Mobile Filter Options
        FilterOption mobile8Inch = new FilterOption("8 inch", screenSizeMobile);
        FilterOption mobile12Inch = new FilterOption( "12 inch", screenSizeMobile);

        FilterOption ram8GBMobile = new FilterOption("8 GB", ramMobile);
        FilterOption ram6GBMobile = new FilterOption("6 GB", ramMobile);

        filterOptionRepository.save(mobile8Inch);
        filterOptionRepository.save(mobile12Inch);
        filterOptionRepository.save(ram8GBMobile);
        filterOptionRepository.save(ram6GBMobile);

        // Inventories
        Inventory macbookPro16 = new Inventory("Macbook Pro 16", "High-performance laptop", 10, 2500, 10, laptop);
        Inventory macbookAir14 = new Inventory("Macbook Air 14", "Lightweight and portable laptop", 15, 1500, 5, laptop);
        Inventory dellXPS13 = new Inventory("Dell XPS 13", "Compact high-performance laptop", 8, 1800, 8, laptop);

        Inventory iPhone16 = new Inventory("iPhone 16", "Latest iPhone model", 20, 1200, 5, mobile);
        Inventory samsungGalaxyS22 = new Inventory("Samsung Galaxy S22", "Flagship Android mobile", 25, 1100, 10, mobile);

        inventoryRepository.save(macbookPro16);
        inventoryRepository.save(macbookAir14);
        inventoryRepository.save(dellXPS13);
        inventoryRepository.save(iPhone16);
        inventoryRepository.save(samsungGalaxyS22);

        // Filter Values for Laptops
        filterValuesRepository.save(new FilterValues(laptop16Inch, macbookPro16)); // 16 inch screen
        filterValuesRepository.save(new FilterValues(ram16GBLaptop, macbookPro16 )); // 16 GB RAM
        filterValuesRepository.save(new FilterValues(spaceGray, macbookPro16 )); // Space Gray color

        filterValuesRepository.save(new FilterValues(laptop14Inch, macbookAir14 )); // 14 inch screen
        filterValuesRepository.save(new FilterValues(ram8GBLaptop, macbookAir14 )); // 8 GB RAM
        filterValuesRepository.save(new FilterValues(white, macbookAir14 )); // White color

        filterValuesRepository.save(new FilterValues( laptop14Inch, dellXPS13)); // 14 inch screen
        filterValuesRepository.save(new FilterValues(ram8GBLaptop, dellXPS13 )); // 8 GB RAM
        filterValuesRepository.save(new FilterValues(silver, dellXPS13 )); // Silver color

        // Filter Values for Mobiles
        filterValuesRepository.save(new FilterValues(mobile8Inch, iPhone16 )); // 8 inch screen
        filterValuesRepository.save(new FilterValues(ram8GBMobile, iPhone16)); // 8 GB RAM

        filterValuesRepository.save(new FilterValues(mobile12Inch, samsungGalaxyS22 )); // 12 inch screen
        filterValuesRepository.save(new FilterValues(ram6GBMobile, samsungGalaxyS22 )); // 6 GB RAM
    }
}
