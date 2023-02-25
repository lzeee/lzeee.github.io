/*
 * MIT License
 *
 * Copyright (c) 2022 Jenkins Infra
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package io.jenkins.pluginhealth.scoring.probes;

import io.jenkins.pluginhealth.scoring.model.Plugin;
import io.jenkins.pluginhealth.scoring.model.ProbeResult;

/**
 * Represents the analyze which can be performed on a plugin
 */
public abstract class Probe {

    /**
     * Starts the analyze on a plugin.
     * Should only be called by the {@link ProbeEngine#run()} method.
     *
     * @param plugin  the plugin on which to perform the analyze
     * @param context holder of information passed across the probes executed on a single plugin
     * @return the result of the analyze in a {@link ProbeResult}
     */
    public final ProbeResult apply(Plugin plugin, ProbeContext context) {
        return doApply(plugin, context);
    }

    /**
     * Perform the analyze on a plugin
     *
     * @param plugin  the plugin on which the analyze is done
     * @param context holder of information passed across the probes executed on a single plugin
     * @return a ProbeResult representing the result of the analyze
     */
    protected abstract ProbeResult doApply(Plugin plugin, ProbeContext context);

    /**
     * Get the key of a probe.
     * Each probe has its own unique key, which is used to get and save the result of the analyze.
     *
     * @return a String representing the unique key of a probe
     */
    public abstract String key();

    /**
     * Get the description of what a probe analyze.
     *
     * @return a String describing the probe's function
     */
    public abstract String getDescription();

    /**
     * Determine whether a probe requires that a plugin has been released to do the analyze.
     *
     * @return a boolean value describing whether release is required
     */
    protected boolean requiresRelease() {
        return false;
    }

    /**
     * Determine whether the analyze of a probe is related to the source code of a plugin.
     *
     * @return a boolean value describing whether source code is related
     */
    protected boolean isSourceCodeRelated() {
        return false;
    }
}
