package com.clickaway.service;

import com.clickaway.entity.Coupon;
import com.clickaway.repository.CouponRepository;
import com.clickaway.service.dto.CouponDTO;
import com.clickaway.transformer.CouponTransformer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class CouponServiceImplTest {
    @InjectMocks
    private CouponServiceImpl couponService;
    @Mock
    private CouponRepository couponRepository;
    @Mock
    private CouponTransformer couponTransformer;

    @Test
    void addCoupon() {
        Long id = 1L;
        String title = "clothes";
        // given
        Coupon Coupon = new Coupon();
        Coupon.setId(id);
        Coupon.setTitle(title);

        // when
        Coupon CouponMock = mock(Coupon.class);
        when(CouponMock.getId()).thenReturn(id);
        when(CouponMock.getTitle()).thenReturn(title);
        when(couponRepository.save(any(Coupon.class)))
                .thenReturn(CouponMock);

        CouponDTO CouponMockDTO = mock(CouponDTO.class);
        when(CouponMockDTO.getId()).thenReturn(id);
        when(CouponMockDTO.getTitle()).thenReturn(title);
        when(couponTransformer.transformToCouponDTO(any(Coupon.class)))
                .thenReturn(CouponMockDTO);

        // then
        CouponDTO CouponDTO = couponService.addCoupon(Coupon);
        assertEquals(CouponDTO.getId(), id);
        assertEquals(CouponDTO.getTitle(), title);
    }

    @Test
    void getCoupon() {
        // given
        Long id = 1L;

        // when
        Coupon CouponMock = mock(Coupon.class);
        when(couponRepository.findById(any(Long.class))).thenReturn(Optional.ofNullable(CouponMock));
        when(CouponMock.getId()).thenReturn(id);

        CouponDTO CouponMockDTO = mock(CouponDTO.class);
        when(CouponMockDTO.getId()).thenReturn(id);
        when(couponTransformer.transformToCouponDTO(any(Coupon.class)))
                .thenReturn(CouponMockDTO);

        // then
        CouponDTO CouponDTO = couponService.getCoupon(id);
        assertEquals(CouponDTO.getId(), id);
    }

    @Test
    void getAllCoupons() {
        List<Coupon> CouponList = new ArrayList<>();
        Coupon Coupon = new Coupon();
        Coupon.setId(1L);
        CouponList.add(Coupon);
        Coupon = new Coupon();
        Coupon.setId(2L);
        CouponList.add(Coupon);

        // given

        // when
        when(couponRepository.findAll()).thenReturn(CouponList);
        CouponDTO CouponMockDTO_1 = mock(CouponDTO.class);
        CouponDTO CouponMockDTO_2 = mock(CouponDTO.class);
        when(CouponMockDTO_1.getId()).thenReturn(1L);
        when(CouponMockDTO_2.getId()).thenReturn(2L);
        when(couponTransformer.transformToCouponDTO(any(Coupon.class)))
                .thenReturn(CouponMockDTO_1, CouponMockDTO_2);

        // then
        List<CouponDTO> allCoupons = couponService.getAllCoupons();
        assertEquals(allCoupons.size(), 2);
        assertEquals(allCoupons.get(1).getId(), 2L);
    }

    @Test
    void deleteCoupon() {
        Long id = 1L;
        couponService.deleteCoupon(id);
        verify(couponRepository, times(1)).deleteById(eq(id));
    }
}