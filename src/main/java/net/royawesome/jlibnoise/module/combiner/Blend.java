/*
 * This file is part of jlibnoise.
 * Original libnoise by Jason Bevins <http://libnoise.sourceforge.net/>
 *
 * Copyright (c) 2011 Garrett Fleenor <http://www.spout.org/>
 * jlibnoise is licensed under the GNU Lesser General Public License.
 *
 * jlibnoise is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * jlibnoise is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package net.royawesome.jlibnoise.module.combiner;

import net.royawesome.jlibnoise.Utils;
import net.royawesome.jlibnoise.exception.NoModuleException;
import net.royawesome.jlibnoise.module.Module;

public class Blend extends Module {
	public Blend() {
		super(3);
	}

	public Module getControlModule() {
		if (SourceModule[2] == null) {
			throw new NoModuleException();
		}
		return SourceModule[2];
	}

	public void setControlModule(Module module) {
		if (module == null) {
			throw new IllegalArgumentException("Control Module cannot be null");
		}
		SourceModule[2] = module;
	}

	@Override
	public int GetSourceModuleCount() {
		return 3;
	}

	@Override
	public double GetValue(double x, double y, double z) {
		if (SourceModule[0] == null) {
			throw new NoModuleException();
		}
		if (SourceModule[1] == null) {
			throw new NoModuleException();
		}
		if (SourceModule[2] == null) {
			throw new NoModuleException();
		}

		double v0 = SourceModule[0].GetValue(x, y, z);
		double v1 = SourceModule[1].GetValue(x, y, z);
		double alpha = (SourceModule[2].GetValue(x, y, z) + 1.0) / 2.0;
		return Utils.LinearInterp(v0, v1, alpha);
	}
}
