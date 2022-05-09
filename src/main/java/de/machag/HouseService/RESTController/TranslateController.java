package de.machag.HouseService.RESTController;

import com.google.cloud.translate.v3.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/translate")
public class TranslateController {

    @GetMapping("/{targetLanguage},{text}")
    @ResponseBody
    public ResponseEntity translateToTargetLanguage(@PathVariable String text, @PathVariable String targetLanguage) throws IOException {
        return ResponseEntity.ok(translateText(targetLanguage, text));
    }

    public static String translateText(String targetLanguage, String text)
            throws IOException {

        String projectId = "mystical-surfer-349707";
        try (TranslationServiceClient client = TranslationServiceClient.create()) {
            LocationName parent = LocationName.of(projectId, "global");

            TranslateTextRequest request =
                    TranslateTextRequest.newBuilder()
                            .setParent(parent.toString())
                            .setMimeType("text/plain")
                            .setTargetLanguageCode(targetLanguage)
                            .addContents(text)
                            .build();

            TranslateTextResponse response = client.translateText(request);

            // Display the translation for each input text provided
            for (Translation translation : response.getTranslationsList()) {
                System.out.printf("Translated text: %s\n", translation.getTranslatedText());
            }
            return response.getTranslationsList().get(0).getTranslatedText();
        }
    }
}
