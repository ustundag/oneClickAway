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
        Coupon coupon = new Coupon();
        coupon.setId(id);
        coupon.setTitle(title);

        // when
        Coupon couponMock = mock(Coupon.class);
        when(couponMock.getId()).thenReturn(id);
        when(couponMock.getTitle()).thenReturn(title);
        when(couponRepository.save(any(Coupon.class)))
                .thenReturn(couponMock);

        CouponDTO couponMockDTO = mock(CouponDTO.class);
        when(couponMockDTO.getId()).thenReturn(id);
        when(couponMockDTO.getTitle()).thenReturn(title);
        when(couponTransformer.transformToCouponDTO(any(Coupon.class)))
                .thenReturn(couponMockDTO);

        // then
        CouponDTO couponDTO = couponService.addCoupon(coupon);
        assertEquals(couponDTO.getId(), id);
        assertEquals(couponDTO.getTitle(), title);
    }

    @Test
    void getCoupon() {
        // given
        Long id = 1L;

        // when
        Coupon couponMock = mock(Coupon.class);
        when(couponRepository.findById(any(Long.class))).thenReturn(Optional.ofNullable(couponMock));
        when(couponMock.getId()).thenReturn(id);

        CouponDTO couponMockDTO = mock(CouponDTO.class);
        when(couponMockDTO.getId()).thenReturn(id);
        when(couponTransformer.transformToCouponDTO(any(Coupon.class)))
                .thenReturn(couponMockDTO);

        // then
        CouponDTO couponDTO = couponService.getCoupon(id);
        assertEquals(couponDTO.getId(), id);
    }

    @Test
    void getAllCoupons() {
        List<Coupon> couponList = new ArrayList<>();
        Coupon coupon = new Coupon();
        coupon.setId(1L);
        couponList.add(coupon);
        coupon = new Coupon();
        coupon.setId(2L);
        couponList.add(coupon);

        // given

        // when
        when(couponRepository.findAll()).thenReturn(couponList);
        CouponDTO couponMockDTO_1 = mock(CouponDTO.class);
        CouponDTO couponMockDTO_2 = mock(CouponDTO.class);
        when(couponMockDTO_1.getId()).thenReturn(1L);
        when(couponMockDTO_2.getId()).thenReturn(2L);
        when(couponTransformer.transformToCouponDTO(any(Coupon.class)))
                .thenReturn(couponMockDTO_1, couponMockDTO_2);

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