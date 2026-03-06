package ee.lilian.words.controller;

import ee.lilian.words.entity.Word;
import ee.lilian.words.repository.QueryLogRepository;
import ee.lilian.words.repository.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class WordController {

    @Autowired
    private WordRepository wordRepository;

    @Autowired
    private QueryLogRepository queryLogRepository;

    @GetMapping("words")
    public List<Word> getWords(){
        return wordRepository.findAll();
    }

    @PostMapping("words") //sõnade lisamine
    public List<Word> addWord(@RequestBody Word word){
        if (word.getId()!=null){
            throw new RuntimeException("Cannot add with ID");
        }
        if (word.getWord().length() < 2){
            throw new RuntimeException("Word must be at least 2 letters");
        }
        wordRepository.save(word);//siin salvestab
        return wordRepository.findAll();//siin on uuenenud seis
    }

    @GetMapping("words/count-three")//loendan kolmetähelisi sõnu
    public int countThreeLetterWords(){
        int count = 0;
        for (Word word : wordRepository.findAll()){
            if (word.getWord().length() == 3){
                count++;
            }
        }
        return count;
    }

    @GetMapping("words/divisible-by-three")//loendan kolmega jagunevaid sõnu
    public int divisibleByThree() {
        int count = 0;
        for (Word word : wordRepository.findAll()) {
            if (word.getWord().length() % 3 == 0) {
                count++;
            }
        }
        return count;
    }

    @GetMapping("words/prime")//kas sõna pikkus on algarv
    public boolean isPrimeLength(@RequestParam String word) {
        int length = word.length();
        if (length < 2) { //kui sõna pikkus on väiksem kui 2
            return false;
        }
        for (int i = 2; i < length; i++) { //proovib jagada sõna pikkust kõikide arvudega 2st length-1ni
            if (length % i == 0) {
                return false;
            }
        }
        return true;
    }
}

