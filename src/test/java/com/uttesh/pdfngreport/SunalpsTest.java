/*
 * Copyright 2015 Uttesh Kumar T.H..
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.uttesh.pdfngreport;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 *
 * @author Uttesh Kumar T.H.
 * 
 * This the simple test without skipped test cases, by this test scenario found bug in library and fixed in 2.0.1 release. reported by SUNALPS
 */
public class SunalpsTest {

    @Test
    public void testA() {
        Assert.assertEquals("A", "B");
    }

    @Test
    public void testB() {
        Assert.assertEquals("B", "B");
    }

    @Test
    public void testC() {
        Assert.assertEquals("C", "c");
    }
}
