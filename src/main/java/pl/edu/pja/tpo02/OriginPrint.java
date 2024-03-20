package pl.edu.pja.tpo02;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("Origin")
public class OriginPrint implements Print{
    @Override
    public String changeWord(String word) {
        return word;
    }
}
