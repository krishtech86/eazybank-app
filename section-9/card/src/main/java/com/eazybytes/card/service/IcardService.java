package com.eazybytes.card.service;

import com.eazybytes.card.dto.CardsDto;

public interface IcardService {

        void createCard(String mobileNumber);

        CardsDto fetchCard(String mobileNumber);

        boolean updateCard(CardsDto cardsDto);

        boolean deleteCard(String mobileNumber);
}
