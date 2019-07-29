package com.parijat.workforce;

import com.parijat.workforce.model.CleanerSet;
import com.parijat.workforce.processor.OptimizationProcessorImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OptimizationProcessorTests
{
    @Autowired
    private OptimizationProcessorImpl optimizationProcessor;

    @Test
    public void Test_getMaximumCleanerCount()
    {
        log.info("Testing Non-Public getMaximumCleanerCount()...");
        Integer actual = ReflectionTestUtils.invokeMethod(optimizationProcessor,"getMaximumCleanerCount",
                25,10);
        Integer expected = 3;
        log.info("Actual : "+actual+", expected : "+expected);
        Assert.assertEquals(expected,actual);
    }

    @Test
    public void Test_findOptimumCombination()
    {
        log.info("Testing Non-Public findOptimumCombination()...");
        CleanerSet actual =
                ReflectionTestUtils.invokeMethod(optimizationProcessor, "findOptimumCombination",
                        25,3,2,10,6);
        CleanerSet expected = new CleanerSet();
        expected.setSenior(2);
        expected.setJunior(1);
        log.info("Actual : "+actual+", expected : "+expected);
        Assert.assertEquals(expected,actual);
    }

    @Test
    public void Test_findOptimumCleanerSet()
    {
        log.info("Testing Non-Public findOptimumCleanerSet()...");
        CleanerSet expected = new CleanerSet();
        expected.setSenior(3);
        expected.setJunior(1);

        CleanerSet actual =
                ReflectionTestUtils.invokeMethod(optimizationProcessor, "findOptimumCleanerSet",
                        35,10,6);

        log.info("Actual : "+actual+", expected : "+expected);
        Assert.assertEquals(expected,actual);

        expected = new CleanerSet();
        expected.setSenior(2);
        expected.setJunior(0);

         actual =
                ReflectionTestUtils.invokeMethod(optimizationProcessor, "findOptimumCleanerSet",
                        20,10,6);

        log.info("Actual : "+actual+", expected : "+expected);
        Assert.assertEquals(expected,actual);

        expected = new CleanerSet();
        expected.setSenior(1);
        expected.setJunior(2);

        actual =
                ReflectionTestUtils.invokeMethod(optimizationProcessor, "findOptimumCleanerSet",
                        22,10,6);

        log.info("Actual : "+actual+", expected : "+expected);
        Assert.assertEquals(expected,actual);

        expected = new CleanerSet();
        expected.setSenior(1);
        expected.setJunior(0);

        actual =
                ReflectionTestUtils.invokeMethod(optimizationProcessor, "findOptimumCleanerSet",
                        5,10,6);

        log.info("Actual : "+actual+", expected : "+expected);
        Assert.assertEquals(expected,actual);
    }

    @Test
    public void Test_getOptimumSolution()
    {
        log.info("Testing Public getOptimumSolution()...");
        List<CleanerSet> expected = new ArrayList<>();
        CleanerSet cleanerSet = new CleanerSet();
        cleanerSet.setSenior(3);
        cleanerSet.setJunior(1);
        expected.add(cleanerSet);

        cleanerSet = new CleanerSet();
        cleanerSet.setSenior(1);
        cleanerSet.setJunior(2);
        expected.add(cleanerSet);

        cleanerSet = new CleanerSet();
        cleanerSet.setSenior(2);
        cleanerSet.setJunior(0);
        expected.add(cleanerSet);

        cleanerSet = new CleanerSet();
        cleanerSet.setSenior(1);
        cleanerSet.setJunior(3);
        expected.add(cleanerSet);

        Integer[] rooms = {35, 21, 17, 28};
        List<CleanerSet> actual = optimizationProcessor.getOptimumSolution(rooms,10,6);

        Assert.assertEquals(expected,actual);
    }

    @Test
    public void Test_CleanerSet()
    {
        CleanerSet set1 = new CleanerSet();
        set1.setJunior(2);
        set1.setSenior(1);

        CleanerSet set2 = new CleanerSet();
        set2.setJunior(2);
        set2.setSenior(1);

        Assert.assertEquals(set1,set2);

        set1.setSenior(10);

        Assert.assertNotEquals(set1,set2);

        set1.setJunior(11);

        Assert.assertNotEquals(set1,set2);
        Assert.assertNotEquals(set1,null);

        Assert.assertEquals(set1,set1);


    }
}
