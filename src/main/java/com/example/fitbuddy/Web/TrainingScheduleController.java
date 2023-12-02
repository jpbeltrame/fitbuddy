package com.example.fitbuddy.Web;

import com.example.fitbuddy.DTO.TrainingScheduleForm;
import com.example.fitbuddy.Entities.TrainingSchedule;
import com.example.fitbuddy.Entities.UserFitbuddy;
import com.example.fitbuddy.Repositories.BuddyRepository;
import com.example.fitbuddy.Repositories.TrainingScheduleRepository;
import com.example.fitbuddy.Repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@AllArgsConstructor
public class TrainingScheduleController {

    private UserRepository userRepository;
    private BuddyRepository buddyRepository;
    private TrainingScheduleRepository trainingScheduleRepository;

    @GetMapping(path = "/app/schedules")
    public String ListTrainingSchedule(Model model, Authentication authentication){
        UserFitbuddy user = userRepository.findUserByEmail(authentication.getName());
        List<TrainingSchedule> trainingSchedules = trainingScheduleRepository.findByBuddyId(user.getId());

        model.addAttribute("user", user);
        model.addAttribute("page", "schedules");
        model.addAttribute("trainingSchedules", trainingSchedules);
        return "app/trainingSchedules";
    }
    @GetMapping(path = "/app/schedules/form")
    public String GetTrainingScheduleForm(Model model){
        model.addAttribute("page", "schedules");

        return "app/trainingSchedulesForm";
    }

    @GetMapping(path = "/app/schedules/{id}")
    public String GetTrainingSchedule(@PathVariable String id, Model model, Authentication authentication){

        TrainingSchedule trainingSchedule = trainingScheduleRepository.getById(id);
        if(trainingSchedule == null) {
            return "redirect:/app/schedules?notfound="+id;
        }

        model.addAttribute("page", "schedules");
        model.addAttribute("trainingSchedule", trainingSchedule);
        model.addAttribute("action", "view");

        return "app/trainingSchedulesForm";
    }


    @PostMapping(path = "/app/schedules", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String CreateTrainingSchedule(Model model, TrainingScheduleForm form, Authentication authentication) {
        TrainingSchedule trainingSchedule = new TrainingSchedule();

        UserFitbuddy user = userRepository.findUserByEmail(authentication.getName());

        trainingSchedule.setOwnerUserId(user.getId());
        trainingSchedule.setBuddiesIds(new ArrayList<String>());
        trainingSchedule.setTitle(form.getTitle());
        trainingSchedule.setLocation(form.getLocation());
        trainingSchedule.setStartAt(form.getStartAt());
        trainingSchedule.setEndAt(form.getEndAt());
        trainingSchedule.setDate(form.getDate());

        trainingScheduleRepository.save(trainingSchedule);

        return "redirect:/app/schedules?created=success";
    }

    @PostMapping(path = "/app/schedules/{id}", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String UpdateTrainingSchedule() { return ""; }

    @PostMapping(path = "/app/schedules/{id}/delete", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String DeleteTrainingSchedule(@PathVariable String id) {

        trainingScheduleRepository.deleteById(id);
        return "redirect:/app/schedules?deleted=success";
    }

    @PostMapping(path = "/app/schedules/{id}/share", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String ShareTrainingSchedule() { return ""; }
}
