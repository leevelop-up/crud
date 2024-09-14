package com.example.crud.repository;


import com.example.crud.domain.QShop;
import com.example.crud.domain.Shop;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.JPQLQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.example.crud.domain.QShop.shop;

@RequiredArgsConstructor
@Repository
public class ShopListRepository {

    private final JPQLQueryFactory queryFactory;

    public Page<Shop> getQueryFactory(String name, String city, String category, String district, Pageable pageable) {
        QShop shop = QShop.shop;

        long total = queryFactory
                .selectFrom(shop)
                .where(
                        ShopName(name),
                        ShopCity(city),
                        ShopCategory(category),
                        ShopDistrict(district)
                )
                .fetchCount();

        List<Shop> shops = queryFactory
                .selectFrom(shop)
                .where(
                        ShopName(name),
                        ShopCity(city),
                        ShopCategory(category),
                        ShopDistrict(district)
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();


        return new PageImpl<>(shops, pageable, total);
    }

    private Predicate ShopDistrict(String district) {
        return district != null ? QShop.shop.district.contains(district) : null;
    }

    private Predicate ShopCategory(String category) {
        return category != null ? QShop.shop.category.contains(category) : null;
    }

    private Predicate ShopCity(String city) {
        return city != null ? QShop.shop.city.contains(city) : null;
    }

    private Predicate ShopName(String name) {
        return name != null ? QShop.shop.name.contains(name) : null;
    }
}
