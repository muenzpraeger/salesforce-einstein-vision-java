package com.winkelmeyer.salesforce.predictivevision.tests;

import java.util.ArrayList;
import java.util.List;

import com.winkelmeyer.salesforce.predictivevision.PredictionService;
import com.winkelmeyer.salesforce.predictivevision.model.Dataset;
import com.winkelmeyer.salesforce.predictivevision.model.Model;
import com.winkelmeyer.salesforce.predictivevision.model.PredictionResult;
import com.winkelmeyer.salesforce.predictivevision.model.Probability;

/**
 * This sample class predicts the association of the given image with the available Datasets. 
 * 
 * @author René Winkelmeyer
 *
 */

public class PredictImages {

	public static void main(String[] args) {

		String beachUrl = "https://photos.smugmug.com/Nature/Landscape/i-hvFwSGh/6/X2/pnorvig-galapagos%20%281%29-X2.jpg";

		PredictionService service = new PredictionService();

		try {

			ArrayList<Model> allModels = new ArrayList<>();

			Dataset[] datasets = service.getDatasets();

			for (Dataset dataset : datasets) {
				Model[] modelsDataset = service.getModels(dataset);
				if (modelsDataset!=null && modelsDataset.length>0) {
					for (Model model : modelsDataset) {
						switch(model.getStatus()) {
						case "SUCCEEDED":
							allModels.add(model);
							break;
						case "FAILURE":
							System.out.println("Model " + model.getModelId() + " failed with message: " + model.getFailureMsg());
							break;
						default:
							System.out.println("Model " + model.getModelId() + " is in status " + model.getStatus());
						}
					}
				}
			}

			if (allModels.size()>0) {

				for (Model model : allModels) {

					PredictionResult result = service.predictUrl(model.getModelId(), beachUrl, "");

					if (result==null || result.getProbabilities()==null) {
						return;
					}

					List<Probability> probabilities = result.getProbabilities();

					for (Probability prob : probabilities) {
						System.out.println("Probability in label " + prob.getLabel() + " is " + prob.getProbability());
					}				
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}



	}

}
