package com.armadillo.team.sunkagame;

import com.armadillo.team.sunkagame.Help.HelpContent;

import junit.framework.Assert;

import org.junit.Test;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class HelpContentTest {

    @Test
    public void testContent() throws Exception {
        HelpContent.DummyItem d = new HelpContent.DummyItem("1", "abc", "Test");
        Assert.assertEquals(d.id, "1");

        for (int i = 0; i < HelpContent.ITEMS.size(); ++i) {
            Assert.assertEquals(HelpContent.ITEMS.get(i).id, Integer.toString(i + 1));
        }
    }
}