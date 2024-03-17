package com.eazybytes.card.service.impl;

import com.eazybytes.card.constant.CardsConstants;
import com.eazybytes.card.dto.CardsDto;
import com.eazybytes.card.entity.Cards;
import com.eazybytes.card.exception.CardAlreadyExistException;
import com.eazybytes.card.exception.ResourceNotFoundException;
import com.eazybytes.card.mapper.CardsMapper;
import com.eazybytes.card.repository.CardaRepository;
import com.eazybytes.card.service.IcardService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class CardsServiceImpl implements IcardService {

    private CardaRepository cardaRepository;
    @Override
    public void createCard(String mobileNumber) {
        Optional<Cards> optionalCard = cardaRepository.findByMobileNumber(mobileNumber);
        if(optionalCard.isPresent())
        {
            throw new CardAlreadyExistException("Card is already exist with given mobileNumber" + mobileNumber);
        }
        cardaRepository.save(createNewCard(mobileNumber));
    }

    private Cards createNewCard(String mobileNumber) {
        Cards newCard = new Cards();
        long randomCardNumber = 100000000000L + new Random().nextInt(900000000);
        newCard.setCardNumber(Long.toString(randomCardNumber));
        newCard.setCardType(CardsConstants.CREDIT_CARD);
        newCard.setMobileNumber(mobileNumber);
        newCard.setTotalLimit(CardsConstants.NEW_CARD_LIMIT);
        newCard.setAmountUsed(0);
        newCard.setAvailableAmount(CardsConstants.NEW_CARD_LIMIT);

        return newCard;
    }

    @Override
    public CardsDto fetchCard(String mobileNumber) {
        Cards cards = cardaRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("cards", "mobileNumber", mobileNumber)
        );
        return CardsMapper.mapToCardsDto(cards, new CardsDto());
    }

    @Override
    public boolean updateCard(CardsDto cardsDto) {
        Cards cards = cardaRepository.findByCardNumber(cardsDto.getCardNumber()).orElseThrow(
                () -> new ResourceNotFoundException("Card", "cardNumber", cardsDto.getCardNumber())
        );
        CardsMapper.mapToCards(cardsDto, cards);
        cardaRepository.save(cards);
        return true;
    }

    @Override
    public boolean deleteCard(String mobileNumber) {
        Cards card = cardaRepository.findByCardNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Cards", "mobileNumber", mobileNumber)
        );
        cardaRepository.deleteById(card.getCardId());
        return true;
    }
}
