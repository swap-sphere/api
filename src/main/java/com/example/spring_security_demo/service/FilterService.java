package com.example.spring_security_demo.service;

import com.example.spring_security_demo.dao.CategoryRepository;
import com.example.spring_security_demo.dao.FilterOptionRepository;
import com.example.spring_security_demo.dao.FilterRepository;
import com.example.spring_security_demo.model.Category;
import com.example.spring_security_demo.model.Filter;
import com.example.spring_security_demo.model.FilterOption;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class FilterService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private static FilterRepository filterRepository;

    @Autowired
    private FilterOptionRepository filterOptionRepository;

    public Filter saveFilter(int categoryId, String filterName) throws BadRequestException {
        Optional<Category> category = categoryRepository.findById(categoryId);
        if (category.isEmpty()){
            throw new BadRequestException("Category Not Present.");
        } else{
            if (category.get().getParent() == null){
                throw new BadRequestException("Filter cannot be created for parent Category");
            } else{
                Filter filter = new Filter(filterName, category.get());
                filterRepository.save(filter);
                return filter;
            }
        }
    }

    public void saveFilterOptions(List<String> filterOptions, Filter filter){
        for(String option : filterOptions){
            FilterOption filterOption = new FilterOption(option,filter);
            filterOptionRepository.save(filterOption);


        }
    }
}
