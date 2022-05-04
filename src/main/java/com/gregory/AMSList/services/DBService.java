package com.gregory.AMSList.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gregory.AMSList.domain.Anime;
import com.gregory.AMSList.domain.BookMark;
import com.gregory.AMSList.domain.User;
import com.gregory.AMSList.domain.enums.Status;
import com.gregory.AMSList.repositories.BookMarkRepository;
import com.gregory.AMSList.repositories.StoryRepository;
import com.gregory.AMSList.repositories.UserRepository;

@Service
public class DBService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private StoryRepository storyRepository;
	
	@Autowired
	private BookMarkRepository registrationRepository;
			
	
	public void instantiate() {
		
		User user1 = new User(null, "Greg", "greg@gmail", "123");
		User user2 = new User(null, "alex", "alex@gmail", "123");
		User user3 = new User(null, "alan", "alan@gmail", "123");
		User user4 = new User(null, "hta", "hta@gmail", "123");
		User user5 = new User(null, "jira", "jira@gmail", "123");
		
		Anime an1 = new Anime(null, "Mushiku", "image", "sitelink", "description", 1.0, 12.0);
		Anime an2 = new Anime(null, "Mushiku2", "image2", "sitelink2", "description2", 1.0, 12.0);
		Anime an3 = new Anime(null, "Mushiku3", "image3", "sitelink3", "description3", 1.0, 12.0);
		
		/*Serie se1 = new Serie(null, "Serie", "Serieimage", "Seriesitelink", "Seriedescription", 1.0, 12.0);
		Serie se2 = new Serie(null, "SerieMushiku2", "Serieimage2", "Seriesitelink2", "Seriedescription2", 1.0, 12.0);
		
		
		Manga mg1 = new Manga(null, "MangaMushiku", "Mangaimage", "Mangasitelink", "Mangadescription", 1.0, 12.0);
		Manga mg2 = new Manga(null, "MangaMushiku2", "Mangaimage2", "Mangasitelink2", "Mangadescription2", 1.0, 12.0);*/
		
	
		storyRepository.saveAll(Arrays.asList(an1, an2, an3));
		
		/*user1.addAnimes(new HashSet<>(Arrays.asList(an1, an2)));
		user1.addMangas(new HashSet<>(Arrays.asList(mg1, mg2)));
		user1.addSeries(new HashSet<>(Arrays.asList(se1, se2)));*/
		
		userRepository.saveAll(Arrays.asList(user1, user2, user3, user4, user5));
		
		BookMark reg1 = new BookMark();
		BookMark reg2 = new BookMark();
		BookMark reg3 = new BookMark();
		
		reg1.setStory(an1);
		reg1.setUser(user1);
		reg1.setStatus(Status.COMPLETED);
		reg1.setStoryType(an1.getDecriminationValue());;
		
		reg2.setStory(an2);
		reg2.setUser(user1);
		reg2.setStatus(Status.PLAN_TO_READ);
		reg2.setStoryType(an2.getDecriminationValue());;
		
		reg3.setStory(an3);
		reg3.setUser(user3);
		reg3.setStatus(Status.PLAN_TO_READ);
		reg3.setStoryType(an3.getDecriminationValue());;
		
		registrationRepository.saveAll(Arrays.asList(reg1, reg2, reg3));
		
		
	}

}
