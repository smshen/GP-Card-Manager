/*
 * Copyright (c) 2011 NullPointer Software
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package au.com.nullpointer.kms;

import java.security.KeyStore;
import java.security.KeyStoreException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * @author shane
 * 
 */
public abstract class KeyManager {

    protected KeyStore store;

    protected abstract void loadKeyStore() throws KeyManagerException;

    public static KeyManager createKeyManager() throws KeyManagerException {
        return new JCEKeyManager();
    }

    protected KeyManager() throws KeyManagerException {
        loadKeyStore();
    }

    public List<String> listKeys() throws KeyManagerException {
        List<String> keys = new ArrayList<String>();

        try {
            Enumeration<String> aliases = store.aliases();
            while (aliases.hasMoreElements()) {
                keys.add(aliases.nextElement());
            }
        } catch (KeyStoreException e) {
            throw new KeyManagerException("Could not retrieve key list", e);
        }

        return keys;
    }
}
