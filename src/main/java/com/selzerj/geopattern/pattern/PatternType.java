package com.selzerj.geopattern.pattern;

import com.selzerj.geopattern.composers.structure.AbstractStructureComposer;
import com.selzerj.geopattern.composers.structure.ChevronsComposer;
import com.selzerj.geopattern.composers.structure.ConcentricCirclesComposer;
import com.selzerj.geopattern.composers.structure.DiamondsComposer;
import com.selzerj.geopattern.composers.structure.HexagonsComposer;
import com.selzerj.geopattern.composers.structure.MosaicSquaresComposer;
import com.selzerj.geopattern.composers.structure.NestedSquaresComposer;
import com.selzerj.geopattern.composers.structure.OctagonsComposer;
import com.selzerj.geopattern.composers.structure.OverlappingCirclesComposer;
import com.selzerj.geopattern.composers.structure.OverlappingRingsComposer;
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
	NESTED_SQUARES(NestedSquaresComposer.class),
	OCTAGONS(OctagonsComposer.class),
	OVERLAPPING_CIRCLES(OverlappingCirclesComposer.class),
	OVERLAPPING_RINGS(OverlappingRingsComposer.class),
	SQUARES(SquaresComposer.class),
	SINE_WAVES(SineWavesComposer.class);

	private final Class<? extends AbstractStructureComposer> structureComposerClass;

	PatternType(Class<? extends AbstractStructureComposer> structureComposerClass) {
		this.structureComposerClass = structureComposerClass;
	}
}
