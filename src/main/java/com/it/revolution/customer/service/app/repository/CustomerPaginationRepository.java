package com.it.revolution.customer.service.app.repository;

import com.it.revolution.customer.service.app.model.entity.Customer;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerPaginationRepository extends PagingAndSortingRepository<Customer, Long> {
}
