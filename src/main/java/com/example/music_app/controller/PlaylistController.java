package com.example.music_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.music_app.model.Playlist;
import com.example.music_app.model.User;
import com.example.music_app.repository.PlaylistRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class PlaylistController {
	
	@Autowired
	PlaylistRepository playlistRepository;
	
	@PostMapping("/create-playlist")
	public String createPLaylist(Model model,@RequestParam("name") String name, HttpSession session ) {
		if (session.getAttribute("loggedIn") != null && (boolean) session.getAttribute("loggedIn")) {
			User user = (User) session.getAttribute("user");
			
			Playlist playlist = new Playlist();
			playlist.setName(name);
			playlist.setUser(user);
			
			playlistRepository.save(playlist);
		}
		
		return "redirect:/";
	}
}
