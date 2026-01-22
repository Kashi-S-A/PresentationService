package com.presentation.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.presentation.entity.Presentation;
import com.presentation.entity.User;
import com.presentation.enums.PresentationStatus;
import com.presentation.enums.Role;
import com.presentation.exception.InvalidOperationException;
import com.presentation.exception.ResourceNotFoundException;
import com.presentation.repository.PresentationRepo;
import com.presentation.repository.UserRepository;
import com.presentation.service.PresentationService;

@Service
public class PresentationServiceImpl implements PresentationService {

    @Autowired
    private PresentationRepo presentationRepo;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Presentation changeStatus(Integer pid, PresentationStatus status) {

        Presentation presentation = presentationRepo.findById(pid)
                .orElseThrow(() -> new ResourceNotFoundException("Presentation not found"));

        presentation.setPresentationStatus(status);
        return presentationRepo.save(presentation);
    }

    @Override
    public Presentation assignPresentationToStudent(Integer adminId, Integer studentId, Presentation presentation) {

        userRepository.findByIdAndRole(adminId, Role.ADMIN)
                .orElseThrow(() -> new ResourceNotFoundException("Admin not found"));

        User student = userRepository.findByIdAndRole(studentId, Role.STUDENT)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));

        presentation.setUser(student);
        presentation.setPresentationStatus(PresentationStatus.ASSIGNED);

        return presentationRepo.save(presentation);
    }

    @Override
    public Presentation getPresentationById(Integer presentationId) {

        return presentationRepo.findById(presentationId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Presentation not found with id: " + presentationId));
    }

    @Override
    public List<Presentation> getPresentationsByStudentId(Integer studentId) {

        User student = userRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));

        if (student.getRole() != Role.STUDENT) {
            throw new InvalidOperationException("User is not a STUDENT");
        }

        return presentationRepo.findByUserId(studentId);
    }
}
