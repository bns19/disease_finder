//package nl.bioinf.diseasefinderSpring.controllers;
//
//import junit.framework.Assert;
//import nl.bioinf.diseasefinderSpring.domain.UserForm;
//import org.junit.Test;
//
///**
// * Created by henridupon on 3/14/2016.
// */
//public class PersonFormTest {
//
//    /**
//     * test authority default.
//     * @throws Exception
//     */
//    @Test
//    public void TestPersonFormAuthority() throws Exception {
//        System.out.println("check default authority");
//        UserForm personform = new UserForm();
//        String authority = personform.getAuthority();
//
//        Assert.assertEquals(authority, "USER");
//    }
//
//    /**
//     * test enabled default.
//     * @throws Exception
//     */
//    @Test
//    public void TestPersonFormEnabled() throws Exception {
//        System.out.println("check default enabled");
//        UserForm personform = new UserForm();
//        String enabled = personform.getEnabled();
//
//        Assert.assertEquals(enabled, "true");
//
//    }
//
//    /**
//     * test changing the default authority value.
//     * @throws Exception
//     */
//    @Test
//    public void TestPersonFormChangeAuthority() throws Exception {
//        System.out.println("change authority");
//        UserForm personform = new UserForm();
//        personform.setAuthority("ADMIN");
//        String authority = personform.getAuthority();
//
//        Assert.assertEquals(authority, "ADMIN");
//    }
//
//    /**
//     * test changing the default enabled value.
//     * @throws Exception
//     */
//    @Test
//    public void TestPersonFormChangeEnabled() throws Exception {
//        System.out.println("change Enabled");
//        UserForm personform = new UserForm();
//        personform.setEnabled("false");
//        String authority = personform.getEnabled();
//
//        Assert.assertEquals(authority, "false");
//    }
//}
