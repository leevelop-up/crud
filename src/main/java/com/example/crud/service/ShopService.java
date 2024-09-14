package com.example.crud.service;

import com.example.crud.domain.Member;
import com.example.crud.domain.Shop;
import com.example.crud.dto.param.ShopRegisterParam;
import com.example.crud.dto.param.ShopUpdateParam;
import com.example.crud.exception.CrudException;
import com.example.crud.repository.ShopJpaRepository;
import com.example.crud.repository.ShopListRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static com.example.crud.exception.ErrorCode.VALUE_NOT_FOUND;

@Service
@RequiredArgsConstructor
@Transactional
public class ShopService {
    private final ShopJpaRepository shopJpaRepository;
    private final ShopListRepository shopListRepository;
    public void registerShop(ShopRegisterParam shopRegisterParam) {
        Shop shop = shopRegisterParam.toDomain();
        shopJpaRepository.save(shop);

    }

    public void updateShop(Integer id, ShopUpdateParam param) {
        Shop shop = shopJpaRepository.findById(id)
                .orElseThrow(() -> {throw new CrudException(VALUE_NOT_FOUND, "Shop not found");});
        shop.updateFromDto(param);
    }

    public Page<Shop> searchShop(String name, String city, String category, String district, Pageable pageable){
        Page<Shop> result;
        if (name != null) {
            result = shopListRepository.getQueryFactory(name,city,category,district,pageable);
        } else {
            result = shopJpaRepository.findAll(pageable);
        }
        return result;
    }


    public void deleteShop(Integer id) {
        shopJpaRepository.findById(id)
                .orElseThrow(() -> {throw new CrudException(VALUE_NOT_FOUND, "Shop not found");});
        shopJpaRepository.deleteById(id);
    }

}
