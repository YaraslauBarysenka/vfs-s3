/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.commons.vfs2.cache;

import org.apache.commons.vfs2.FileName;
import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSystem;
import org.apache.commons.vfs2.FilesCache;

/**
 * Tests for {@link NullFilesCache} used by {@link NullFilesCacheTestCase}.
 */
public class NullFilesCacheTests extends FilesCacheTestsBase
{
    public void testFilesCache() throws Exception
    {
        final FileObject scratchFolder = getWriteFolder();

        final FileObject dir1 = scratchFolder.resolveFile("dir1");
        final FileObject dir1_2 = scratchFolder.resolveFile("dir1");

        assertFalse("Should always be new instance with NullCache", dir1 == dir1_2);
    }

    @Override
    public void testBasicCacheOps() throws Exception
    {
        // the basic test looks different for a null cache:

        final FilesCache cache = getManager().getFilesCache();
        final FileObject fo = getWriteFolder().resolveFile("dir1");
        final FileName fn = fo.getName();
        final FileSystem fs = fo.getFileSystem();

        cache.clear(fs);
        assertNull(cache.getFile(fs, fn));

        cache.putFile(fo);
        assertNull(null, cache.getFile(fs, fn));

        assertFalse(cache.putFileIfAbsent(fo)); // hmmm?
        assertNull(null, cache.getFile(fs, fn));

        cache.removeFile(fs, fn);
        assertNull(cache.getFile(fs, fn));
    }


    public void testClass()
    {
        assertTrue(getManager().getFilesCache() instanceof NullFilesCache);
    }
}
