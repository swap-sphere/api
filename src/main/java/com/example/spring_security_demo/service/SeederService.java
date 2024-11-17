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
        // Categories
        Category electronics = new Category(1, "Electronics", null);
        Category laptop = new Category(2, "Laptop", electronics);
        Category mobile = new Category(3, "Mobile", electronics);

        categoryRepository.save(electronics);
        categoryRepository.save(laptop);
        categoryRepository.save(mobile);

        // Filters
        Filter screenSizeLaptop = new Filter(1, "Screen size", laptop);
        Filter ramLaptop = new Filter(2, "RAM", laptop);
        Filter colorLaptop = new Filter(3, "Color", laptop);

        Filter screenSizeMobile = new Filter(4, "Screen size", mobile);
        Filter ramMobile = new Filter(5, "RAM", mobile);

        filterRepository.save(screenSizeLaptop);
        filterRepository.save(ramLaptop);
        filterRepository.save(colorLaptop);
        filterRepository.save(screenSizeMobile);
        filterRepository.save(ramMobile);

        // Filter Options
        // Laptop Filter Options
        FilterOption laptop16Inch = new FilterOption(1, "16 inch", screenSizeLaptop);
        FilterOption laptop14Inch = new FilterOption(8, "14 inch", screenSizeLaptop);

        FilterOption ram16GBLaptop = new FilterOption(2, "16 GB", ramLaptop);
        FilterOption ram8GBLaptop = new FilterOption(9, "8 GB", ramLaptop);

        FilterOption spaceGray = new FilterOption(3, "Space Gray", colorLaptop);
        FilterOption white = new FilterOption(4, "White", colorLaptop);
        FilterOption silver = new FilterOption(5, "Silver", colorLaptop);

        filterOptionRepository.save(laptop16Inch);
        filterOptionRepository.save(laptop14Inch);
        filterOptionRepository.save(ram16GBLaptop);
        filterOptionRepository.save(ram8GBLaptop);
        filterOptionRepository.save(spaceGray);
        filterOptionRepository.save(white);
        filterOptionRepository.save(silver);

        // Mobile Filter Options
        FilterOption mobile8Inch = new FilterOption(6, "8 inch", screenSizeMobile);
        FilterOption mobile12Inch = new FilterOption(10, "12 inch", screenSizeMobile);

        FilterOption ram8GBMobile = new FilterOption(7, "8 GB", ramMobile);
        FilterOption ram6GBMobile = new FilterOption(11, "6 GB", ramMobile);

        filterOptionRepository.save(mobile8Inch);
        filterOptionRepository.save(mobile12Inch);
        filterOptionRepository.save(ram8GBMobile);
        filterOptionRepository.save(ram6GBMobile);

        // Inventories
        Inventory macbookPro16 = new Inventory(1, "Macbook Pro 16", "High-performance laptop", 10, 2500, 10, laptop);
        Inventory macbookAir14 = new Inventory(3, "Macbook Air 14", "Lightweight and portable laptop", 15, 1500, 5, laptop);
        Inventory dellXPS13 = new Inventory(4, "Dell XPS 13", "Compact high-performance laptop", 8, 1800, 8, laptop);

        Inventory iPhone16 = new Inventory(2, "iPhone 16", "Latest iPhone model", 20, 1200, 5, mobile);
        Inventory samsungGalaxyS22 = new Inventory(5, "Samsung Galaxy S22", "Flagship Android mobile", 25, 1100, 10, mobile);

        inventoryRepository.save(macbookPro16);
        inventoryRepository.save(macbookAir14);
        inventoryRepository.save(dellXPS13);
        inventoryRepository.save(iPhone16);
        inventoryRepository.save(samsungGalaxyS22);

        // Filter Values for Laptops
        filterValuesRepository.save(new FilterValues(1, macbookPro16, laptop16Inch)); // 16 inch screen
        filterValuesRepository.save(new FilterValues(2, macbookPro16, ram16GBLaptop)); // 16 GB RAM
        filterValuesRepository.save(new FilterValues(3, macbookPro16, spaceGray)); // Space Gray color

        filterValuesRepository.save(new FilterValues(4, macbookAir14, laptop14Inch)); // 14 inch screen
        filterValuesRepository.save(new FilterValues(5, macbookAir14, ram8GBLaptop)); // 8 GB RAM
        filterValuesRepository.save(new FilterValues(6, macbookAir14, white)); // White color

        filterValuesRepository.save(new FilterValues(7, dellXPS13, laptop14Inch)); // 14 inch screen
        filterValuesRepository.save(new FilterValues(8, dellXPS13, ram8GBLaptop)); // 8 GB RAM
        filterValuesRepository.save(new FilterValues(9, dellXPS13, silver)); // Silver color

        // Filter Values for Mobiles
        filterValuesRepository.save(new FilterValues(10, iPhone16, mobile8Inch)); // 8 inch screen
        filterValuesRepository.save(new FilterValues(11, iPhone16, ram8GBMobile)); // 8 GB RAM

        filterValuesRepository.save(new FilterValues(12, samsungGalaxyS22, mobile12Inch)); // 12 inch screen
        filterValuesRepository.save(new FilterValues(13, samsungGalaxyS22, ram6GBMobile)); // 6 GB RAM
    }
}
