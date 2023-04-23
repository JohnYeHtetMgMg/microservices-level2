package com.john.moviecatalogservice.resources;

import com.john.moviecatalogservice.models.CatalogItem;
import com.john.moviecatalogservice.models.UserRating;
import com.john.moviecatalogservice.services.MovieInfo;
import com.john.moviecatalogservice.services.UserRatingInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

    @Autowired
    MovieInfo movieInfo;
    @Autowired
    UserRatingInfo userRatingInfo;
    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {
        UserRating ratings = userRatingInfo.getUserRating(userId);
        return ratings.getUserRating()
                .stream()
                .map(rating -> movieInfo.getCatalogItem(rating))
                .collect(Collectors.toList());
    }
}
