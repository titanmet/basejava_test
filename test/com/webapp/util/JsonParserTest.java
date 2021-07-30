package com.webapp.util;

import com.webapp.model.Resume;
import com.webapp.model.Section;
import com.webapp.model.TextSection;
import org.junit.Assert;
import org.junit.Test;

import static com.webapp.TestData.*;
import static org.junit.Assert.*;

public class JsonParserTest {

    @Test
    public void testResume() {
        String json = JsonParser.write(R1);
        System.out.println(json);
        Resume resume = JsonParser.read(json, Resume.class);
        Assert.assertEquals(R1, resume);
    }

    @Test
    public void write() {
        Section section1 = new TextSection("Objective1");
        String json = JsonParser.write(section1, Section.class);
        System.out.println(json);
        Section section2 = JsonParser.read(json, Section.class);
        Assert.assertEquals(section1, section2);
    }
}