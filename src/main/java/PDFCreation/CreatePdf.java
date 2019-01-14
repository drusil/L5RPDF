package PDFCreation;

import model.CharacterInfo;
import model.CharacterSheet;
import model.ElementRing;
import model.Rings;
import model.Skill;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.awt.Point;
import java.io.File;
import java.util.List;

import static PDFCreation.LineOffsets.CharacterInfoOffsets.CLAN_OFFSET;
import static PDFCreation.LineOffsets.CharacterInfoOffsets.INSIGHT_OFFSET;
import static PDFCreation.LineOffsets.CharacterInfoOffsets.NAME_OFFSET;
import static PDFCreation.LineOffsets.CharacterInfoOffsets.SCHOOL_OFFSET;
import static PDFCreation.LineOffsets.RingsOffset.AGILITY_OFFSET;
import static PDFCreation.LineOffsets.RingsOffset.AIR_OFFSET;
import static PDFCreation.LineOffsets.RingsOffset.ATTRIBUTE_FONT_SIZE;
import static PDFCreation.LineOffsets.RingsOffset.AWARNESS_OFFSET;
import static PDFCreation.LineOffsets.RingsOffset.EARTH_OFFSET;
import static PDFCreation.LineOffsets.RingsOffset.FIRE_OFFSET;
import static PDFCreation.LineOffsets.RingsOffset.INTELIGENCE_OFFSET;
import static PDFCreation.LineOffsets.RingsOffset.PERCEPTION_OFFSET;
import static PDFCreation.LineOffsets.RingsOffset.REFLEXES_OFFSET;
import static PDFCreation.LineOffsets.RingsOffset.RING_FONT_SIZE;
import static PDFCreation.LineOffsets.RingsOffset.STAMINA_OFFSET;
import static PDFCreation.LineOffsets.RingsOffset.STRENGTH_OFFSET;
import static PDFCreation.LineOffsets.RingsOffset.VOID_OFFSET;
import static PDFCreation.LineOffsets.RingsOffset.WATER_OFFSET;
import static PDFCreation.LineOffsets.RingsOffset.WILLPOWER_OFFSET;
import static PDFCreation.LineOffsets.SkillsOffset.SKILL_NEXT_LINE_OFFSET;
import static PDFCreation.LineOffsets.SkillsOffset.SKILL_RANK_X_OFFSET;
import static PDFCreation.LineOffsets.SkillsOffset.SKILL_SPEC_X_OFFSET;
import static PDFCreation.LineOffsets.SkillsOffset.SKILL_TEXT_X_OFFSET;
import static PDFCreation.LineOffsets.SkillsOffset.SKILL_Y_OFFSET;

public class CreatePdf {
    private static final String TEMPLATE_CHARACTER_SHEET_NAME = "l5r_CharacterSheet.pdf";
    private static final PDFont FONT = PDType1Font.HELVETICA;
    private static final int FONT_SIZE = 10;
    private CharacterSheet characterSheet;


    public CreatePdf(CharacterSheet characterSheet) {
        this.characterSheet = characterSheet;
    }

    public void create(String newFileName) throws Exception {


        try (PDDocument doc = PDDocument.load(new File(TEMPLATE_CHARACTER_SHEET_NAME))) {
            System.out.println("Loaded");
            createCharacterInfo(doc);
            createRings(doc);
            createSkills(doc);

            doc.save(newFileName
                    + ".pdf");
        }
    }


    private void createCharacterInfo(PDDocument doc) throws Exception {
        CharacterInfo charInfo = characterSheet.getCharacterInfo();

        //Name
        put(charInfo.getName() + " " + charInfo.getFamily(),
                NAME_OFFSET, 1, doc);

        //Clan
        put(charInfo.getClan(), CLAN_OFFSET, 1, doc);

        //School
        put(charInfo.getSchool(), SCHOOL_OFFSET, 1, doc);

        //Insigt
        put(characterSheet.getInsight() + "", INSIGHT_OFFSET, 1, doc);

    }

    private void createRings(PDDocument doc) throws Exception {
        Rings rings = characterSheet.getRings();
        ElementRing elementRing;

        //Air
        elementRing = rings.getRing("Air");
        put(elementRing.getValue() + "", AIR_OFFSET,
                RING_FONT_SIZE, 1, doc);
        put(elementRing.getPhysicalAttributeValue() + "", REFLEXES_OFFSET,
                ATTRIBUTE_FONT_SIZE, 1, doc);
        put(elementRing.getMentalAttributeValue() + "",
                AWARNESS_OFFSET, ATTRIBUTE_FONT_SIZE, 1, doc);

        //Earth
        elementRing = rings.getRing("Earth");
        put(elementRing.getValue() + "", EARTH_OFFSET,
                RING_FONT_SIZE, 1, doc);
        put(elementRing.getPhysicalAttributeValue() + "", STAMINA_OFFSET,
                ATTRIBUTE_FONT_SIZE, 1, doc);
        put(elementRing.getMentalAttributeValue() + "",
                WILLPOWER_OFFSET, ATTRIBUTE_FONT_SIZE, 1, doc);

        //Water
        elementRing = rings.getRing("Water");
        put(elementRing.getValue() + "", WATER_OFFSET,
                RING_FONT_SIZE, 1, doc);
        put(elementRing.getPhysicalAttributeValue() + "", STRENGTH_OFFSET,
                ATTRIBUTE_FONT_SIZE, 1, doc);
        put(elementRing.getMentalAttributeValue() + "",
                PERCEPTION_OFFSET, ATTRIBUTE_FONT_SIZE, 1, doc);

        //Fire
        elementRing = rings.getRing("Fire");
        put(elementRing.getValue() + "", FIRE_OFFSET,
                RING_FONT_SIZE, 1, doc);
        put(elementRing.getPhysicalAttributeValue() + "", AGILITY_OFFSET,
                ATTRIBUTE_FONT_SIZE, 1, doc);
        put(elementRing.getMentalAttributeValue() + "",
                INTELIGENCE_OFFSET, ATTRIBUTE_FONT_SIZE, 1, doc);

        put(rings.getRingOfVoid().getValue() + "", VOID_OFFSET,
                RING_FONT_SIZE, 1, doc);
    }

    private void createSkills(PDDocument doc) throws Exception {

        List<Skill> skills = characterSheet.getSkills().getListOfSkills();

        float y_offset = SKILL_Y_OFFSET;

        for (Skill s : skills) {
            createSkill(s, y_offset, doc);
            y_offset -= SKILL_NEXT_LINE_OFFSET;
        }

    }

    private void createSkill(Skill skill, float y_offset, PDDocument doc) throws Exception {

        FPoint startingPoint = new FPoint(SKILL_TEXT_X_OFFSET, y_offset);
        put(skill.getName(), startingPoint, 1, doc);

        startingPoint = new FPoint(SKILL_RANK_X_OFFSET, y_offset);
        put(skill.getValue() + "", startingPoint, 1, doc);

        startingPoint = new FPoint(SKILL_SPEC_X_OFFSET, y_offset);
        put(skill.getSpecializationsToString(), startingPoint, 1, doc);

    }

    private void put(String text, Point startingPoint,
                     int pageNumber, PDDocument doc) throws Exception {
        PDPage page = doc.getPage(pageNumber - 1);

        try (PDPageContentStream contentStream = new PDPageContentStream(doc, page, true, true, true)) {

            contentStream.beginText();
            contentStream.setFont(FONT, FONT_SIZE);
            contentStream.newLineAtOffset((float) startingPoint.getX(), (float) startingPoint.getY());
            contentStream.showText(text);
            contentStream.endText();
        }


    }

    private void put(String text, FPoint startingPoint,
                     int pageNumber, PDDocument doc) throws Exception {
        PDPage page = doc.getPage(pageNumber - 1);

        try (PDPageContentStream contentStream = new PDPageContentStream(doc, page, true, true, true)) {

            contentStream.beginText();
            contentStream.setFont(FONT, FONT_SIZE);
            contentStream.newLineAtOffset(startingPoint.getX(), startingPoint.getY());
            contentStream.showText(text);
            contentStream.endText();
        }


    }

    private void put(String text, Point startingPoint, int fontSize,
                     int pageNumber, PDDocument doc) throws Exception {
        PDPage page = doc.getPage(pageNumber - 1);

        try (PDPageContentStream contentStream = new PDPageContentStream(doc, page, true, true, true)) {

            contentStream.beginText();
            contentStream.setFont(FONT, fontSize);
            contentStream.newLineAtOffset((float) startingPoint.getX(), (float) startingPoint.getY());
            contentStream.showText(text);
            contentStream.endText();
        }


    }
}
