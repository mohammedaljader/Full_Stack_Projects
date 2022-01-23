package org.project.joboffers;

import org.modelmapper.ModelMapper;
import org.project.joboffers.DALInterfaces.ICategoryDAL;
import org.project.joboffers.DALInterfaces.IFavoriteJobDAL;
import org.project.joboffers.DALInterfaces.IJobApplicationDAL;
import org.project.joboffers.DALInterfaces.IJobDAL;
import org.project.joboffers.DTO.*;
import org.project.joboffers.Model.*;
import org.project.joboffers.ServiceInterfaces.IUserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@SpringBootApplication
public class JoboffersApplication {

	public static void main(String[] args) {
		SpringApplication.run(JoboffersApplication.class, args);
	}


	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

	@Bean
	ModelMapper modelMapper(){
		return new ModelMapper();
	}


	@Bean
	CommandLineRunner commandLineRunner(IUserService userService, ICategoryDAL categoryDAL, IJobDAL jobDAL, IJobApplicationDAL jobApplicationDAL, IFavoriteJobDAL favoriteJobDAL) {
		return args -> {
			RoleDTO roleJob_publisher = new RoleDTO("Job_publisher");
			RoleDTO roleJobSeeker = new RoleDTO("Job_seeker");
			RoleDTO roleAdmin = new RoleDTO("Admin");
			userService.addRole(roleJob_publisher);
			userService.addRole(roleJobSeeker);
			userService.addRole(roleAdmin);
			UserDTO admin = new UserDTO("Mo", "Mo@gmail.com", "aass321456", "Mo@gmail.com", "Mo", roleAdmin.getRoleName());
			UserDTO jobSeeker = new UserDTO("test", "test", "aass321456", "test@gmail.com", "Eindhoven", roleJobSeeker.getRoleName());
			UserDTO jobPublisher = new UserDTO("mohammed", "mo.sy06", "aass321456", "maljader18@gmail.com", "Eindhoven", roleJob_publisher.getRoleName());
			userService.addUser(admin);
			userService.addUser(jobPublisher);
			userService.addUser(jobSeeker);
			Category partTime = new Category("7d013b4a-8061-45d9-b0f4-47897e219901", "PartTime");
			Category fullTime = new Category("7d014b4a-8061-45d9-b0f4-47897e219901", "FullTime");
			categoryDAL.addCategory(fullTime);
			categoryDAL.addCategory(partTime);
//		    Job software_engineer = new Job("Software Engineer","Software Engineer",12.99,"Amsterdam",fullTime,jobPublisher);
//			Job game_developer = new Job("Game developer","Game developer",10.00,"Eindhoven",fullTime,jobPublisher);
//			jobDAL.addJob(software_engineer);
//			jobDAL.addJob(game_developer);
//			JobApplication jobApplication = new JobApplication(jobSeeker, software_engineer,  "111.pdf", "i love this work",LocalDateTime.now());
//			jobApplicationDAL.addApplication(jobApplication);
//			FavoriteJob favoriteJob = new FavoriteJob(jobSeeker, software_engineer, LocalDateTime.now());
//			favoriteJobDAL.addJobToList(favoriteJob);
		};
	}
}
