package com.academy.beerinventory.web.controller;

import com.academy.beerinventory.domain.Beer;
import com.academy.beerinventory.web.model.BeerDto;
import com.academy.beerinventory.web.service.IBeerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/beer")
public class BeerController {

    private IBeerService beerService;

    public BeerController(IBeerService beerService) {
        this.beerService = beerService;
    }

    @GetMapping("/")
    public ResponseEntity <List<BeerDto>> getBeers(){
        List<BeerDto> beersFromService = beerService.findAll();
        return new ResponseEntity<List<BeerDto>>(beersFromService, HttpStatus.OK);
    }

    @GetMapping("/{beerId}")
    public ResponseEntity<BeerDto> getBeer(@PathVariable Long beerId){
        BeerDto beerDto = beerService.findById(beerId);
        return new ResponseEntity<>(beerDto, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<BeerDto> addBeer(@RequestBody BeerDto beer) {
        BeerDto beerCreated = beerService.addBeer(beer);
        return new ResponseEntity<>(beerCreated, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BeerDto> updateBeer(@PathVariable long id, @RequestBody BeerDto beer) {
        BeerDto beerUpdated = beerService.updateBeer(id, beer);
        return new ResponseEntity<>(beerUpdated, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteBeer(@PathVariable long id) {
        beerService.deleteBeer(id);
        return new ResponseEntity(id, HttpStatus.OK);
    }

}
