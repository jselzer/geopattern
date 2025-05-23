package io.github.jselzer.geopattern.model;

import io.github.jselzer.geopattern.internal.composers.structure.AbstractStructureComposer;
import io.github.jselzer.geopattern.internal.composers.structure.ChevronsComposer;
import io.github.jselzer.geopattern.internal.composers.structure.ConcentricCirclesComposer;
import io.github.jselzer.geopattern.internal.composers.structure.DiamondsComposer;
import io.github.jselzer.geopattern.internal.composers.structure.HexagonsComposer;
import io.github.jselzer.geopattern.internal.composers.structure.MosaicSquaresComposer;
import io.github.jselzer.geopattern.internal.composers.structure.NestedSquaresComposer;
import io.github.jselzer.geopattern.internal.composers.structure.OctagonsComposer;
import io.github.jselzer.geopattern.internal.composers.structure.OverlappingCirclesComposer;
import io.github.jselzer.geopattern.internal.composers.structure.OverlappingRingsComposer;
import io.github.jselzer.geopattern.internal.composers.structure.PlaidComposer;
import io.github.jselzer.geopattern.internal.composers.structure.PlusSignsComposer;
import io.github.jselzer.geopattern.internal.composers.structure.SineWavesComposer;
import io.github.jselzer.geopattern.internal.composers.structure.SquaresComposer;
import io.github.jselzer.geopattern.internal.composers.structure.TessellationComposer;
import io.github.jselzer.geopattern.internal.composers.structure.TrianglesComposer;
import io.github.jselzer.geopattern.internal.composers.structure.XesComposer;
import lombok.Getter;

/**
 * The PatternType enum represents the available patterns.
 */
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
	PLAID(PlaidComposer.class),
	PLUS_SIGNS(PlusSignsComposer.class),
	SINE_WAVES(SineWavesComposer.class),
	SQUARES(SquaresComposer.class),
	TESSELLATION(TessellationComposer.class),
	TRIANGLES(TrianglesComposer.class),
	XES(XesComposer.class);

	private final Class<? extends AbstractStructureComposer> structureComposerClass;

	PatternType(Class<? extends AbstractStructureComposer> structureComposerClass) {
		this.structureComposerClass = structureComposerClass;
	}
}
