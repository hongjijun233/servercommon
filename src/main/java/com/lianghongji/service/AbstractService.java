package com.lianghongji.service;

import com.lianghongji.Exception.BusinessRuntimeException;
import com.lianghongji.Exception.CommonErrorCode;
import com.lianghongji.util.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 *抽象Service
 *
 * @author  liang.hongji
 */

@Service
public abstract class AbstractService<R extends JpaRepository<T, ID>, T, ID extends Serializable>{

    private static final Logger LOG = LoggerFactory.getLogger(AbstractService.class);

    @Autowired
    protected R repository;

    private T entity;

    @Transactional
    public void save(T entity){
        repository.save(entity);
    }

    @Transactional
    public void delete(ID id){
        repository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public T find(ID id){
        Optional<T> result = repository.findById(id);
        if (result.isPresent()){
            return result.get();
        }
        return null;
    }

    @Transactional(readOnly = true)
    public List<T> findAll(){
        return repository.findAll();
    }

    @Transactional
    public void update(T entity){
        repository.saveAndFlush(entity);
    }

    /**
     * 当找不到时返回异常
     * @param id
     * @param errorCode
     * @return
     */
    @Transactional(readOnly = true)
    public T find(ID id, CommonErrorCode errorCode){
        T result = find(id);
        if (result == null){
            throw new BusinessRuntimeException(errorCode);
        }
        return result;
    }

    public  boolean exits(String name, Object value, Class<T> domainObject) {
        ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreNullValues();
        T entityInstance = null;
        try {
            entityInstance =  domainObject.newInstance();
            org.apache.commons.beanutils.BeanUtils.setProperty(entityInstance, name, value);
        } catch (Exception e) {
            LOG.error("exits method error ", e);
            throw new BusinessRuntimeException(CommonErrorCode.UNKOWN);
        }
        return repository.exists(Example.of(entityInstance, matcher));
    }

    /**
     * 根据id查找对象，如果找不到抛出相应的CommonErrorCode，如果找到将dto的属性值复制到该对象中
     *
     * @param id
     * @param dto
     * @param errorCode
     * @return
     */
    @Transactional
    public <S> T editAfterFind(ID id, S dto, CommonErrorCode errorCode){
        T entity = this.find(id, errorCode);
        BeanUtils.copyProperties(dto, entity);
        return repository.save(entity);
    }
}
