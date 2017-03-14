package com.winkelmeyer.salesforce.einsteinevision.tests;

import java.io.File;
import java.util.List;

import com.winkelmeyer.salesforce.einsteinvision.PredictionService;
import com.winkelmeyer.salesforce.einsteinvision.model.Dataset;
import com.winkelmeyer.salesforce.einsteinvision.model.Example;
import com.winkelmeyer.salesforce.einsteinvision.model.Label;

/**
 * This sample class trains the Salesforce Einstein Vision Service with the given images within local folders. 
 * 
 * @author René Winkelmeyer
 *
 */

public class CreateAndTrainData {

	public static void main(String[] args) {

		try {

			PredictionService service = new PredictionService();

			Dataset ds = service.createDataset("Beaches and Mountains", new String[] {"Beaches", "Mountains"});

			if (ds==null) {
				return;
			}

			List<Label> labels = ds.getLabelSummary().getLabels();

			int labelIdBeaches = 0;
			int labelIdMountains = 0;

			for (Label label : labels) {
				switch(label.getName()) {
				case "Beaches":
					labelIdBeaches = label.getId();
				case "Mountains":
					labelIdMountains = label.getId();
				}
			}

			File dirBeaches = new File("/Users/rwinkelmeyer/Downloads/TestData/Beaches");
			File dirMountains = new File("/Users/rwinkelmeyer/Downloads/TestData/Mountains");

			int i = 0;

			if (dirBeaches.isDirectory()) {
				File[] filesBeaches = dirBeaches.listFiles();
				for (File fileBeach : filesBeaches) {
					Example example = service.createExample(ds.getId(), "Beach" + i, labelIdBeaches, fileBeach.getAbsolutePath());
					if (example==null) {
						return;
					}
					i = i + 1;
				}
			}

			i = 0;

			if (dirMountains.isDirectory()) {
				File[] filesMountains = dirMountains.listFiles();
				for (File fileMountain : filesMountains) {
					Example example = service.createExample(ds.getId(), "Mountain" + i, labelIdMountains, fileMountain.getAbsolutePath());
					if (example==null) {
						return;
					}
					i = i + 1;
				}
			}

			service.trainDataset(ds.getId(), "Test Training", 0, 0, "");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
