package com.kristijangeorgiev.softdelete.repository;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author Kristijan Georgiev
 *
 * @param <T>
 *            the class of the entity
 * @param <ID>
 *            the ID class of the entity
 * 
 *            NoRepositoryBean interface for the soft delete functionality
 */

@Transactional
@NoRepositoryBean
public interface SoftDeletesRepository<T, ID extends Serializable> extends PagingAndSortingRepository<T, ID> {

	Iterable<T> findAllActive();

	Iterable<T> findAllActive(Sort sort);

	Page<T> findAllActive(Pageable pageable);

	Iterable<T> findAllActive(Iterable<ID> ids);

	T findOneActive(ID id);

	Iterable<T> findActive(Specification<T> spec);

	Iterable<T> findActive(Specification<T> spec, Sort sort);

	Page<T> findActive(Specification<T> spec, Pageable pageable);

	Iterable<T> findActive(Specification<T> spec, Iterable<ID> ids);

	T findOneActive(Specification<T> spec);

	T findOneActive(Specification<T> spec, ID id);

	@Modifying
	void softDelete(ID id);

	@Modifying
	void softDelete(T entity);

	@Modifying
	void softDelete(Iterable<? extends T> entities);

	@Modifying
	void softDeleteAll();

	@Modifying
	void scheduleSoftDelete(ID id, LocalDateTime localDateTime);

	@Modifying
	void scheduleSoftDelete(T entity, LocalDateTime localDateTime);

	long countActive();

	boolean existsActive(ID id);

}