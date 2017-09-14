package com.a3isummit.objects;

/**
 * Created by Siddharth on 14-09-2017.
 */

public class TestimonialObject
{
    public static final TestimonialObject[] TEMP_DATA = {
            new TestimonialObject("Test Name 1", "Testimonial 1!!! Blah Blah Blah Blah Blah Blah Blah Blah Blah ", "14-09-2017"),
            new TestimonialObject("Test Name 2", "Testimonial 2!!! Blah Blah Blah Blah Blah Blah Blah Blah Blah Blah Blah Blah Blah Blah Blah Blah Blah Blah Blah Blah Blah Blah Blah Blah Blah Blah Blah Blah Blah Blah Blah Blah Blah Blah Blah Blah Blah Blah ", "14-09-2017"),
            new TestimonialObject("Test Name 3", "Testimonial 3!!! Blah Blah ", "14-09-2017"),
            new TestimonialObject("Test Name 4", "Testimonial 4!!! Blah Blah Blah Blah Blah Blah Blah Blah Blah Blah Blah Blah Blah Blah Blah Blah Blah Blah Blah Blah Blah Blah Blah Blah Blah ", "14-09-2017"),
    };

    public String name, text, date;

    public TestimonialObject(String name, String text, String date) {
        this.text = text;
        this.name = name;
        this.date = date;
    }
}
