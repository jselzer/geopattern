package com.selzerj.geopattern.pattern;

import com.selzerj.geopattern.composers.structure.AbstractStructureComposer;
import com.selzerj.geopattern.composers.structure.ChevronsComposer;
import com.selzerj.geopattern.composers.structure.ConcentricCirclesComposer;
import com.selzerj.geopattern.composers.structure.DiamondsComposer;
import com.selzerj.geopattern.composers.structure.HexagonsComposer;
import com.selzerj.geopattern.composers.structure.MosaicSquaresComposer;
import com.selzerj.geopattern.composers.structure.SineWavesComposer;
import com.selzerj.geopattern.composers.structure.SquaresComposer;
import lombok.Getter;

@Getter
public enum PatternType {
	CHEVRONS(ChevronsComposer.class),
	CONCENTRIC_CIRCLES(ConcentricCirclesComposer.class),
	DIAMONDS(DiamondsComposer.class),
	HEXAGONS(HexagonsComposer.class),
	MOSAIC_SQUARES(MosaicSquaresComposer.class),
	SQUARES(SquaresComposer.class),
	SINE_WAVES(SineWavesComposer.class);

	private final Class<? extends AbstractStructureComposer> structureComposerClass;

	PatternType(Class<? extends AbstractStructureComposer> structureComposerClass) {
		this.structureComposerClass = structureComposerClass;
	}
}
