package com.selzerj.geopattern.pattern;

import com.selzerj.geopattern.composers.structure.AbstractStructureComposer;
import com.selzerj.geopattern.composers.structure.SquaresComposer;
import lombok.Getter;

@Getter
public enum PatternType {
	SQUARES(SquaresComposer.class);

	private final Class<? extends AbstractStructureComposer> structureComposerClass;

	PatternType(Class<? extends AbstractStructureComposer> structureComposerClass) {
		this.structureComposerClass = structureComposerClass;
	}
}
