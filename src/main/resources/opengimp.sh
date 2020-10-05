if [[ -f "assets/minecraft/textures/entity/signs/$1.xcf" ]]; then
	gimp assets/minecraft/textures/entity/signs/$1.xcf &
	sleep 10
	gimp assets/treemendous/textures/entity/boat/$1.xcf &
	gimp assets/treemendous/textures/block/stripped_$1_log_top.xcf &
	gimp assets/treemendous/textures/block/$1_door_top.xcf &
	gimp assets/treemendous/textures/block/$1_log.xcf &
	gimp assets/treemendous/textures/block/$1_leaves.xcf &
	gimp assets/treemendous/textures/block/stripped_$1_log.xcf &
	gimp assets/treemendous/textures/block/$1_sapling.xcf &
	gimp assets/treemendous/textures/block/$1_log_top.xcf &
	gimp assets/treemendous/textures/block/$1_trapdoor.xcf &
	gimp assets/treemendous/textures/block/$1_door_bottom.xcf &
	gimp assets/treemendous/textures/block/$1_planks.xcf &
	gimp assets/treemendous/textures/item/$1_door.xcf &
	gimp assets/treemendous/textures/item/$1_sign.xcf &
	gimp assets/treemendous/textures/item/$1_boat.xcf &
else
	echo "file not found: $1"
fi
