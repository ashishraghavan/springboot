package org.ashish.springboot.practice.repository;

import org.ashish.springboot.practice.model.Player;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

//extends PagingAndSortingRepository<Player,Long>
public interface NbaRepository extends CrudRepository<Player,Long> {

}
