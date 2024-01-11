package com.examle.esa.repository.base;


import com.examle.esa.entity.base.BaseLink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;


@NoRepositoryBean
public interface BaseLinkRepo<T extends BaseLink, ID> extends JpaRepository<T, ID> {

    T findOneByUniqueId(ID id);

}