package com.selzerj.geopattern.pattern;

import com.selzerj.geopattern.composers.structure.AbstractStructureComposer;
import com.selzerj.geopattern.composers.structure.ChevronsComposer;
import com.selzerj.geopattern.composers.structure.ConcentricCirclesComposer;
import com.selzerj.geopattern.composers.structure.SineWavesComposer;
import com.selzerj.geopattern.composers.structure.SquaresComposer;
import lombok.Getter;

@Getter
public enum PatternType {
	CHEVRONS(ChevronsComposer.class),
	CONCENTRIC_CIRCLES(ConcentricCirclesComposer.class),
	SQUARES(SquaresComposer.class),
	SINE_WAVES(SineWavesComposer.class);

	private final Class<? extends AbstractStructureComposer> structureComposerClass;

	PatternType(Class<? extends AbstractStructureComposer> structureComposerClass) {
		this.structureComposerClass = structureComposerClass;
	}
}
