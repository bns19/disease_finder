/**
 * Project: Disease Finder
 * Theme 11/12
 * Created by Mariska Slofstra & Arne Roeters
 * Adjusted by Bas Sikkema & Henri du Pon
 */
package nl.bioinf.diseasefinderSpring.bodyfeatures;

import java.util.Arrays;

/**
 * FeatureCollectiion collects all the main features and their specific features
 * which are possible in a disease.
 *
 * @author mkslofstra
 */
public class FeatureCollection {

    /**
     * globalFeatures are the groups of features.
     */
    private String[] globalFeatures;

    /**
     * The getter of globalFeatures.
     *
     * @return globalFeatures: a list of string which are the main groups of the
     * features.
     */
    public final String[] getGlobalFeatures() {
        return globalFeatures;
    }

    /**
     * FeatureCollection is the function of FeatureCollection which is called
     * when the class is called. This function calls fillGlobalFeatures so that
     * this will be filled everytime when the class is called.
     *
     * @author mkslofstra
     */
    public FeatureCollection() {
        this.fillGlobalFeatures();
    }

    /**
     * fillGlobal features fills the global variable globalFeatures.
     *
     * @author mkslofstra
     */
    private void fillGlobalFeatures() {
        globalFeatures = new String[]{"growth", "headAndNeck",
            "cardiovascular", "respiratory", "chest", "abdomen",
            "genitourinary", "skeletal", "skinNailsHair", "muscleSoftTissue",
            "neurologic", "voice", "metabolicFeatures", "endocrineFeatures",
            "hematology", "immunology", "neoplasia", "prenatalManifestations",
            "laboratoryAbnormalities", "miscellaneous", "molecularBasis",
            "oldFormat"};
    }

    /**
     * extendFeatures extends the main features with more specific features.
     *
     * @param feature is the feature which is asked.
     * @return extended a list of strings in which the more specific features of
     * a main feature are.
     * @author mkslofstra
     */
    public final String[] extendFeature(final String feature) {
        String[] extended = new String[]{};
        if (!Arrays.asList(globalFeatures).contains(feature)) {
            throw new IllegalArgumentException(feature + "is not a global "
                    + "feature of diseases on omim.");
        }
        BodyFeature bodyFeature = BodyFeature.valueOf(feature);
        switch (bodyFeature) {
            case growth:
                extended = new String[]{"growthHeight", "growthWeight",
                    "growthOther"};
                break;
            case headAndNeck:
                extended = new String[]{"headAndNeckHead", "headAndNeckFace",
                    "headAndNeckEars", "headAndNeckEyes", "headAndNeckNose",
                    "headAndNeckMouth", "headAndNeckTeeth", "headAndNeckNeck"
                };
                break;
            case cardiovascular:
                extended = new String[]{"cardiovascularHeart",
                    "cardiovascularVascular"};
                break;
            case respiratory:
                extended = new String[]{"respiratoryNasopharynx",
                    "respiratoryLarynx", "respiratoryAirways",
                    "respiratoryLung"};
                break;
            case chest:
                extended = new String[]{"chestExternalFeatures",
                    "chestRibsSternumClaviclesAndScapulae",
                    "chestBreasts", "chestDiaphragm"};
                break;
            case abdomen:
                extended = new String[]{"abdomenExternalFeatures",
                    "abdomenLiver", "abdomenPancreas", "abdomenBiliaryTract",
                    "abdomenSpleen", "abdomenGastrointestinal"};
                break;
            case genitourinary:
                extended = new String[]{"genitourinaryExternalGenitaliaMale",
                    "genitourinaryExternalGenitaliaFemale",
                    "genitourinaryInternalGenitaliaMale",
                    "genitourinaryInternalGenitaliaFemale",
                    "genitourinaryKidneys", "genitourinaryUreters",
                    "genitourinaryBladder"
                };
                break;
            case skeletal:
                extended = new String[]{"skeletalSkull", "skeletalSpine",
                    "skeletalPelvis", "skeletalLimbs", "skeletalHands",
                    "skeletalFeet"};
                break;
            case skinNailsHair:
                extended = new String[]{"skinNailsHairSkin",
                    "skinNailsHairSkinHistology",
                    "skinNailsHairSkinElectronMicroscopy", "skinNailsHairNails",
                    "skinNailsHairHair"
                };
                break;
            case muscleSoftTissue:
                extended = new String[]{"muscleSoftTissue"};
                break;
            case neurologic:
                extended = new String[]{"neurologicCentralNervousSystem",
                    "neurologicPeripheralNervousSystem",
                    "neurologicBehavioralPsychiatricManifestations"};
                break;
            case voice:
                extended = new String[]{"voice"};
                break;
            case metabolicFeatures:
                extended = new String[]{"metabolicFeatures"};
                break;
            case endocrineFeatures:
                extended = new String[]{"endocrineFeatures"};
                break;
            case hematology:
                extended = new String[]{"hematology"};
                break;
            case immunology:
                extended = new String[]{"immunology"};
                break;
            case neoplasia:
                extended = new String[]{"neoplasia"};
                break;
            case prenatalManifestations:
                extended = new String[]{"prenatalManifestationsMovement",
                    "prenatalManifestationsAmnioticFluid",
                    "prenatalManifestationsPlacentaAndUmbilicalCord",
                    "prenatalManifestationsMaternal",
                    "prenatalManifestationsDelivery"};
                break;
            case laboratoryAbnormalities:
                extended = new String[]{"laboratoryAbnormalities"};
                break;
            case miscellaneous:
                extended = new String[]{"miscellaneous"};
                break;
            case molecularBasis:
                extended = new String[]{"molecularBasis"};
                break;
            case oldFormat:
                extended = new String[]{"oldFormat"};
                break;
            default:
                System.err.println("No extended value found for: " + feature);
        }
        return extended;
    }

}
