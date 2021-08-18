package com.example.pdfedit;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.task.configuration.EnableTask;
import org.springframework.context.annotation.Bean;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.io.ByteArrayOutputStream;
import com.itextpdf.text.xml.xmp.XmpWriter;
import java.util.*;
import java.util.ArrayList;
import com.itextpdf.text.pdf.AcroFields.Item;
import com.itextpdf.text.pdf.PdfAcroForm.*;
import com.itextpdf.kernel.pdf.PdfDocument.*;
import java.lang.String;

@SpringBootApplication
@EnableTask
public class PdfEditApplication {

	@Bean
	public CommandLineRunner commandLineRunner() {
		return new PdfEditCommandLineRunner();
	}

	public static void main(String[] args) {
		SpringApplication.run(PdfEditApplication.class, args);
	}

	public static class PdfEditCommandLineRunner implements CommandLineRunner {
		@Override
		public void run(String... strings) throws IOException, DocumentException {
			try {
				System.out.println("----------------- Task Application Start ------------------");
				PdfReader reader = new PdfReader("test.pdf");
				PdfStamper stamper = new PdfStamper(reader, new FileOutputStream("output.pdf"));
				// Get Number of Pages
				int numberOfPages = stamper.getReader().getNumberOfPages();
				for(int i = 1; i <= numberOfPages; i++) {
//					System.out.println("Page No:" + i);
					String textForm = PdfTextExtractor.getTextFromPage(reader, i);
//					System.out.println(textForm);
				}
				AcroFields fields = stamper.getAcroFields();
				Set<String> fieldKeys = fields.getFields().keySet();
				for (String itemKey : fieldKeys) {
					fields.setField("Text1", "Text Field 1");
					fields.setField("Text2", "Text Field 2");
					fields.setField("Text5", "Text Field 5");
					fields.setField("Text6", "Text Field 6");
					fields.setField("Text7", "Text Field 7");
					fields.setField("Text8", "Text Field 8");
					fields.setField("Text20", "Text Field 20");
					fields.setField("Text19", "Text Field 19");
					System.out.println(itemKey + ":" + fields.getField(itemKey));
				}
				stamper.close();
			} catch (DocumentException ex) {
				System.out.println("ERROR");
			}
		}
	}

}
