/**
 * Project: Disease Finder
 * Theme 11/12
 * Created by Mariska Slofstra & Arne Roeters
 * Adjusted by Bas Sikkema & Henri du Pon
 */
package nl.bioinf.diseasefinderSpring.bodyFeatures;

/**
 * BodyFeature is the enum which specifies all the main features of a disease.
 *
 * @author mkslofstra
 */
public enum BodyFeature {

    /**
     * growth: The growth feature is the information about the growth if this is
     * affected by a given disease.
     */
    growth,
    /**
     * headAndNeck: The headAndNeck feature is the information about the head
     * and neck if these features are affected by the disease.
     */
    headAndNeck,
    /**
     * cardiovascular: The cardiovascular feature is the information about the
     * cardiovascular part of the body if this is affected by the disease.
     */
    cardiovascular,
    /**
     * respiratory: The respiratory feature is the information about the
     * respiratory system of the body if this is affected by the disease.
     */
    respiratory,
    /**
     * chest: The chest feature is the information about the chest if the chest
     * is affected by the disease.
     */
    chest,
    /**
     * abdomen: the information about the abdomen if these are affected by the
     * disease.
     */
    abdomen,
    /**
     * genitourinary: the information about the genitourinary features if these
     * are affected by the disease.
     */
    genitourinary,
    /**
     * skeletal: the information about the skeleton if this is abnormally formed
     * when a person has the disease.
     */
    skeletal,
    /**
     * skinNailsHair: the information about the skin, hair and nails if these
     * are abnormal in the disease.
     */
    skinNailsHair,
    /**
     * muscleSoftTissue: the abnormalities of the muscle and soft tissue in the
     * disease.
     */
    muscleSoftTissue,
    /**
     * neurologic: the abnormalities in the neurologic system in the disease.
     */
    neurologic,
    /**
     * voice: the abnormalities of the voice if these are present in the
     * disease.
     */
    voice,
    /**
     * metabolicFeatures: the metabolic features of the disease.
     */
    metabolicFeatures,
    /**
     * endocrineFeatures: the endocrine features of the disease.
     */
    endocrineFeatures,
    /**
     * hematology: the information about the hematology if this is abnormal in
     * the disease.
     */
    hematology,
    /**
     * immunology: the information about the immunology if this if affected by
     * the disease.
     */
    immunology,
    /**
     * neoplasia: the information about the neoplasia if this is affected by the
     * disease.
     */
    neoplasia,
    /**
     * prenatalManifestations: tells if there are prenatal manifestations
     * present in the disease.
     */
    prenatalManifestations,
    /**
     * laboratoryAbnormalities: the abnormalities which can be found in the lab
     * when a person has the disease.
     */
    laboratoryAbnormalities,
    /**
     * miscellaneous: the miscellaneous information about the disease.
     */
    miscellaneous,
    /**
     * molecularBasis: the molecular basis of the disease.
     */
    molecularBasis,
    /**oldFormat: if this is true, all the information about the disease is
     in one piece of text.*/
    oldFormat
}
