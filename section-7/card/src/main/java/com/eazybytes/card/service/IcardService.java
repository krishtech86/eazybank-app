package com.eazybytes.card.service;

import com.eazybytes.card.dto.CardsDto;
import org.springframework.stereotype.Repository;

@Repository
public interface IcardService {

        void createCard(String mobileNumber);

        CardsDto fetchCard(String mobileNumber);

        boolean updateCard(CardsDto cardsDto);

        boolean deleteCard(String mobileNumber);
}
