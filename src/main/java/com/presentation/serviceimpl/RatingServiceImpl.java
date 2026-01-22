package com.presentation.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.presentation.entity.Presentation;
import com.presentation.entity.Rating;
import com.presentation.entity.User;
import com.presentation.enums.PresentationStatus;
import com.presentation.enums.Role;
import com.presentation.exception.InvalidOperationException;
import com.presentation.exception.ResourceNotFoundException;
import com.presentation.repository.PresentationRepo;
import com.presentation.repository.RatingRepo;
import com.presentation.repository.UserRepository;
import com.presentation.service.RatingService;

@Service
public class RatingServiceImpl implements RatingService {

    @Autowired
    private RatingRepo ratingRepo;

    @Autowired
    private PresentationRepo presentationRepo;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Rating createRating(Integer presentationId, Rating rating) {

        Presentation presentation = presentationRepo.findById(presentationId)
                .orElseThrow(() -> new ResourceNotFoundException("Presentation not found"));

        if (presentation.getUser().getRole() != Role.STUDENT) {
            throw new InvalidOperationException("User must be STUDENT");
        }

        double totalScore = rating.getCommunication()
                + rating.getConfidence()
                + rating.getContent()
                + rating.getInteraction()
                + rating.getLiveliness()
                + rating.getUsageProps();

        double averageScore = totalScore / 6;

        presentation.setPresentationTotalScore(averageScore);
        presentation.setPresentationStatus(PresentationStatus.COMPLETED);
        presentationRepo.save(presentation);

        User user = presentation.getUser();
        List<Presentation> presentations = user.getPresentations();

        double userTotalScore = presentations.stream()
                .mapToDouble(Presentation::getPresentationTotalScore)
                .sum();

        user.setUserTotalScore(userTotalScore / presentations.size());
        userRepository.save(user);

        rating.setPresentation(presentation);
        return ratingRepo.save(rating);
    }

    @Override
    public Rating getRatingsByPresentation(Integer presentationId) {

        Presentation presentation = presentationRepo.findById(presentationId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Presentation not found with id: " + presentationId));

        return presentation.getRating();
    }
}
