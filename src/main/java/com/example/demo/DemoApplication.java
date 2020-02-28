package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.couchbase.core.query.N1qlPrimaryIndexed;
import org.springframework.data.couchbase.core.query.ViewIndexed;
import org.springframework.data.couchbase.repository.CouchbaseRepository;

import javax.annotation.PostConstruct;
import javax.print.Doc;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

@SpringBootApplication
public class DemoApplication {

	@Autowired
	private  SharesRepositary sharesRepositary;

	@Autowired
	private SharesGapUpsRepositary sharesGapUpsRepositary;

	@PostConstruct
	public  void insert(){
		/*final List <String> readFromCSV = readFromCSV("/home/rohit.goyla/Downloads/cm20FEB2020bhav.csv");
		*//*SYMBOL,SERIES,OPEN,HIGH,LOW,CLOSE,LAST,PREVCLOSE,TOTTRDQTY,TOTTRDVAL,TIMESTAMP,TOTALTRADES,ISIN
		MFSL,EQ,542,597.05,542,565,562.75,516.75,26621595,15236192427.55,20-FEB-2020,307862,INE180A01020*//*
		readFromCSV.remove(0);
		final String[] split = readFromCSV.get(0).split(",");
		List <ShareDetails> mfsl = sharesRepositary.findById("MFSL").get().getShareDetails();
		mfsl.add(ShareDetails.builder()
				.OPEN(Double.valueOf(split[2]))
				.HIGH(Double.valueOf(split[3]))
				.LOW(Double.valueOf(split[4]))
				.CLOSE(Double.valueOf(split[5]))
				.PREVIOUS_CLOSE(Double.valueOf(split[7]))
				//
				.WEEK_HIGH_52(Double.valueOf(split[7]))
				.PREVIOUSCLOSE_TO_OPEN(Double.valueOf(split[7]))
                .OPEN_TO_HIGH(Double.valueOf(split[0]))
                .OPEN_TO_LOW(Double.valueOf(split[0]))
                .OPEN_TO_CLOSE(Double.valueOf(split[0]))
				.build());
		sharesRepositary.save(SharesDTO.builder()
				.SHARENAME("MFSL")
				.shareDetails(mfsl)
				.build());*/
		/*final List <String> stringList = readTextFile("/home/rohit.goyla/Downloads/Quote-Equity-MFSL-EQ-21-02-2019-to-21-02-2020.csv");
		stringList.remove(0);
		final List <ShareDetails> collect = stringList.stream().map(shares -> {
			final String[] split = shares.split(",");
			Double gapUp = (Double.valueOf(split[2].replace("\"", "").trim())) - (Double.valueOf(split[5].replace("\"", "").trim()));

			SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
			String date = split[0].replace("\"", "");
			Date currentDate = null;
			try {
				currentDate = formatter.parse(date);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			return ShareDetails.builder()
					.DATE(currentDate)
					.OPEN(Double.valueOf(split[2].replace("\"", "").trim()))
					.HIGH(Double.valueOf(split[3].replace("\"", "").trim()))
					.LOW((Double.valueOf(split[4].replace("\"", "").trim())))
					.CLOSE((Double.valueOf(split[7].replace("\"", "").trim())))
					.WEEK_HIGH_52((Double.valueOf(split[9].replace("\"", "").trim())))
					.WEEK_LOW_52((Double.valueOf(split[10].replace("\"", "").trim())))
					.PREVIOUS_CLOSE(Double.valueOf(split[5].replace("\"", "").trim()))
					.OPEN_TO_HIGH(Double.valueOf(split[3].replace("\"", "").trim()) - Double.valueOf(split[2].replace("\"", "").trim()))
					.OPEN_TO_LOW(Double.valueOf(split[4].replace("\"", "").trim()) - Double.valueOf(split[2].replace("\"", "").trim()))
					.OPEN_TO_CLOSE(Double.valueOf(split[7].replace("\"", "").trim()) - Double.valueOf(split[2].replace("\"", "").trim()))
					.PREVIOUSCLOSE_TO_OPEN(Double.valueOf(split[2].replace("\"", "").trim()) - Double.valueOf(split[5].replace("\"", "").trim()))
					.GAP_UP(gapUp)
					.build();
		}).collect(Collectors.toList());
		sharesRepositary.save(SharesDTO.builder()
				.SHARENAME("MFSL")
				.shareDetails(collect)
				.build());
		final Optional <SharesDTO> ioc = sharesRepositary.findById("MFSL");
		final List <ShareDetails> shareDetails = ioc.get().getShareDetails();

		final List <ShareDetails> gap_ups = shareDetails.stream()
				.sorted(Comparator.comparingDouble(ShareDetails::getGAP_UP).reversed())
				.limit(5)
				.collect(Collectors.toList());

		sharesGapUpsRepositary.save(SharesGapUps.builder()
				.SHARENAME("MFSL_gap_ups")
				.SHAREDETAILS(gap_ups)
				.build());

		final Optional <SharesGapUps> ioc_gap_ups = sharesGapUpsRepositary.findById("MFSL_gap_ups");
		System.out.println("ioc_gap_ups.get().getSHAREDETAILS().size()::: "+ioc_gap_ups.get().getSHAREDETAILS().get(0));*/
	}
	public static void main(String[] args) {
		/*final List <String> stringList = readTextFile("/home/rohit.goyla/Downloads/Quote-Equity-MFSL-EQ-20-02-2019-to-20-02-2020.csv");
		//"Date ","series ","OPEN ","HIGH ","LOW ","PREV. CLOSE ","ltp ","close ","vwap ","52W H ","52W L ","VOLUME ","VALUE ","No of trades "
		//"19-Feb-2020","EQ","502.00","523.00","501.35","502.15","522.00","516.75","513.91","568.20","370.05",1547819,"795,434,488.60",34547
		stringList.remove(0);

		final String findGapPlusHike = stringList.get(0);
		final String[] strings = findGapPlusHike.split(",");
		final Double OPEN = Double.valueOf(strings[2].replace("\"", "").trim());
		final Double PREVIOUS_CLOSE = Double.valueOf(strings[5].replace("\"", "").trim());
		final Double CLOSE = Double.valueOf(strings[7].replace("\"", "").trim());

		final double diffOpenPlusPreviousClose = PREVIOUS_CLOSE - OPEN;
		final double diffPercentageOpenPlusPreviousClose = diffOpenPlusPreviousClose / 100 * OPEN;

		final double diffClosePlusPreviousClose = CLOSE - OPEN;
		final double diffPercentageClosePlusPreviousClose = diffClosePlusPreviousClose / 100 * OPEN;

		System.out.println("diffPercentageOpenPlusPreviousClose:: "+diffPercentageOpenPlusPreviousClose);
		System.out.println("diffPercentageClosePlusPreviousClose:: "+diffPercentageClosePlusPreviousClose);*/


		/*final List <SharesDTO> collect = stringList.stream().map(shares -> {
			final String[] split = shares.split(",");
			Double gapUp = (Double.valueOf(split[2].replace("\"", "").trim())) - (Double.valueOf(split[5].replace("\"", "").trim()));
			return SharesDTO.builder()
					.OPEN(Double.valueOf(split[2].replace("\"", "").trim()))
					.PREVIOUS_CLOSE(Double.valueOf(split[5].replace("\"", "").trim()))
					.GAP_UP(gapUp)
					.build();
		}).collect(Collectors.toList());

		final List <SharesDTO> sharesDTOList = collect.stream()
				.sorted(Comparator.comparingDouble(SharesDTO::getGAP_UP).reversed())
				.limit(3)
				.collect(Collectors.toList());*/

		System.out.println("DONE");
		SpringApplication.run(DemoApplication.class, args);
	}
	public static List<String> readTextFile(final String fileName) {
		List<String> shareList = new ArrayList<String>();
		try {
			BufferedReader in = new BufferedReader(new FileReader(fileName));
			String line;
			while ((line = in.readLine()) != null) {
				shareList.add(line);
			}
			in.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return shareList;
	}

	public static List<String> readFromCSV(String fileName) {
		List<String> stringList = new ArrayList<String>();
		try {
			BufferedReader br = null;
			String line = "";
			String cvsSplitBy = ",";
			br = new BufferedReader(new FileReader(fileName));
			while ((line = br.readLine()) != null) {
				stringList.add(line);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return stringList;
	}
}