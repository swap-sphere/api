package com.example.spring_security_demo.service;

import com.example.spring_security_demo.dao.CategoryRepository;
import com.example.spring_security_demo.dao.FilterOptionRepository;
import com.example.spring_security_demo.dao.FilterRepository;
import com.example.spring_security_demo.model.Category;
import com.example.spring_security_demo.model.Filter;
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

    public void seed() {
        Category electronics = new Category("Electronics", null);
        categoryRepository.save(electronics);

        Category sports = new Category("Sports", null);
        categoryRepository.save(sports);

        Category laptop = new Category("Laptop", electronics);
        categoryRepository.save(laptop);

        Filter brandLaptop = new Filter("Brand", laptop);
//        FilterOption apple = new FilterOption("Apple", brandLaptop);
//        FilterOption lenovo = new FilterOption("Lenovo", brandLaptop);
//        FilterOption dell = new FilterOption("Dell", brandLaptop);

//        filterOptionRepository.save(apple);
//        filterOptionRepository.save(lenovo);
//        filterOptionRepository.save(dell);

        Filter ramLaptop = new Filter("RAM", laptop);
        Filter screenSize = new Filter("Screen Size", laptop);
        Filter ssd = new Filter("SSD", laptop);

        filterRepository.save(ramLaptop);
        filterRepository.save(screenSize);
        filterRepository.save(ssd);
        filterRepository.save(brandLaptop);

        Category mobile = new Category("Mobile", electronics);
        categoryRepository.save(mobile);

        Filter ramPhone = new Filter("RAM", mobile);
        Filter storage = new Filter("ROM", mobile);
        Filter brandMobile = new Filter("Brand", mobile);

        filterRepository.save(ramPhone);
        filterRepository.save(storage);
        filterRepository.save(brandMobile);

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
