from PIL import Image, ImageOps, ImageColor
import json
import os
import random



class Filter:
    def __init__(self, filter_settings, variables):
        self.variables = variables

        self.has_mask = False
        if ("img_mask" in filter_settings):
            self.has_mask = True
            self.mask = Image.open(filter_settings["img_mask"])

    def apply(self, image):
        pass  # override in superclass



class FilterColorize(Filter):
    def __init__(self, filter_settings, variables):
        super().__init__(filter_settings, variables)

        color_var = filter_settings["color"]
        color_var = color_var[1:len(color_var)]
        self.color = self.variables[color_var]

    def apply(self, image):
        image = image.convert("RGBA")
        pixels = image.load()
        colorize_color = ImageColor.getrgb(self.color)
        if (self.has_mask):
            mask_pixels = self.mask.load()

        for x in range(image.width):
            for y in range(image.height):
                if (self.has_mask and mask_pixels[x, y] == (0, 0, 0, 255)):
                    continue
                r, g, b, a = pixels[x, y]
                r = int(r * (colorize_color[0] / 255))
                g = int(g * (colorize_color[1] / 255))
                b = int(b * (colorize_color[2] / 255))
                pixels[x, y] = (r, g, b, a)

        return image



class FilterNoise(Filter):
    def __init__(self, filter_settings, variables):
        super().__init__(filter_settings, variables)

        self.hue = int(360 * filter_settings["hue"])
        self.saturation = int(100 * filter_settings["saturation"])
        self.value = int(100 * filter_settings["value"])
        self.seed = filter_settings["seed"]

    def apply(self, image):
        hsv_image = image.convert(mode="HSV")
        pixels = hsv_image.load()
        if (self.has_mask):
            mask_pixels = self.mask.load()
        
        random.seed(self.seed)
        for x in range(image.width):
            for y in range(image.height):
                if (self.has_mask and mask_pixels[x, y] == (0, 0, 0, 255)):
                    continue
                h, s, v = pixels[x, y]
                h += int(self.hue * random.uniform(-1, 1))
                s += int(self.saturation * random.uniform(-1, 1))
                v += int(self.value * random.uniform(-1, 1))
                pixels[x, y] = (h, s, v)

        image = hsv_image.convert(mode="RGBA")
        return image



class FilterOverlay(Filter):
    def __init__(self, filter_settings, variables):
        super().__init__(filter_settings, variables)

        self.overlay_path = filter_settings["src"]

    def apply(self, image):
        overlay_image = Image.open(self.overlay_path)
        image_pixels = image.load()
        overlay_pixels = overlay_image.load()
        if (self.has_mask):
            mask_pixels = self.mask.load()

        for x in range(image.width):
            for y in range(image.height):
                if (self.has_mask and mask_pixels[x, y] == (0, 0, 0, 255)):
                    continue
                r, g, b, a = image_pixels[x, y]
                over_r, over_g, over_b, over_a = overlay_pixels[x, y]
                r = int(((r / 255) * ((255 - over_a) / 255) + (over_r / 255) * (over_a / 255)) * 255)
                g = int(((g / 255) * ((255 - over_a) / 255) + (over_g / 255) * (over_a / 255)) * 255)
                b = int(((b / 255) * ((255 - over_a) / 255) + (over_b / 255) * (over_a / 255)) * 255)
                image_pixels[x, y] = (r, g, b, a)

        image = Image.alpha_composite(image, overlay_image)

        overlay_image.close()
        return image



class FilterCutout(Filter):
    def __init__(self, filter_settings, variables):
        super().__init__(filter_settings, variables)

    def apply(self, image):
        image_pixels = image.load()
        mask_pixels = self.mask.load()

        for x in range(image.width):
            for y in range(image.height):
                if (mask_pixels[x, y] == (0, 0, 0, 255)):
                    image_pixels[x, y] = (0, 0, 0, 0)

        return image



class FilterHSV(Filter):
    def __init__(self, filter_settings, variables):
        super().__init__(filter_settings, variables)

        self.hue = int(360 * filter_settings["hue"])
        self.saturation = int(100 * filter_settings["saturation"])
        self.value = int(100 * filter_settings["value"])

    def apply(self, image):
        hsv_image = image.convert(mode="HSV")
        image_pixels = hsv_image.load()
        if (self.has_mask):
            mask_pixels = self.mask.load()

        for x in range(image.width):
            for y in range(image.height):
                if (self.has_mask and mask_pixels[x, y] == (0, 0, 0, 255)):
                    continue
                h, s, v = image_pixels[x, y]
                h += self.hue
                s += self.saturation
                v += self.value
                image_pixels[x, y] = (h, s, v)

        image = hsv_image.convert(mode="RGBA")
        return image



class Wood:
    def __init__(self, json_dict):
        self.json_dict = json_dict
        self.wood_name = self.get_wood_name()
        self.variables = self.get_variables()
        self.textures_dict = self.get_textures_dict()

    def generate_textures(self, base_output_dir):
        self.generate_texture("planks", base_output_dir + "/treemendous/textures/block/{}_planks.png".format(self.wood_name))
        self.generate_texture("wood", base_output_dir + "/treemendous/textures/block/{}_log.png".format(self.wood_name))
        self.generate_texture("sign_item", base_output_dir + "/treemendous/textures/item/{}_sign.png".format(self.wood_name))
        self.generate_texture("sign_entity", base_output_dir + "/minecraft/textures/entity/signs/{}.png".format(self.wood_name))
        self.generate_texture("boat_entity", base_output_dir + "/treemendous/textures/entity/boat/{}.png".format(self.wood_name))
        self.generate_texture("boat_item", base_output_dir + "/treemendous/textures/item/{}_boat.png".format(self.wood_name))
        self.generate_texture("log_top", base_output_dir + "/treemendous/textures/block/{}_log_top.png".format(self.wood_name))
        self.generate_texture("stripped_log_top", base_output_dir + "/treemendous/textures/block/stripped_{}_log_top.png".format(self.wood_name))
        self.generate_texture("stripped_wood", base_output_dir + "/treemendous/textures/block/stripped_{}_log.png".format(self.wood_name))
        self.generate_texture("trapdoor", base_output_dir + "/treemendous/textures/block/{}_trapdoor.png".format(self.wood_name))
        self.generate_texture("door_item", base_output_dir + "/treemendous/textures/item/{}_door.png".format(self.wood_name))
        self.generate_texture("door_top", base_output_dir + "/treemendous/textures/block/{}_door_top.png".format(self.wood_name))
        self.generate_texture("door_bottom", base_output_dir + "/treemendous/textures/block/{}_door_bottom.png".format(self.wood_name))
        self.generate_texture("chest", base_output_dir + "/treemendous/textures/entity/chest/{}.png".format(self.wood_name))
        self.generate_texture("chest_left", base_output_dir + "/treemendous/textures/entity/chest/{}_left.png".format(self.wood_name))
        self.generate_texture("chest_right", base_output_dir + "/treemendous/textures/entity/chest/{}_right.png".format(self.wood_name))

    def generate_texture(self, texture_name, path):
        image_gen_settings = self.textures_dict[texture_name][0]
        if (image_gen_settings["type"] == "img"):
            img = Image.open(image_gen_settings["src"])

        for i in range(1, len(self.textures_dict[texture_name])):
            filter_settings = self.textures_dict[texture_name][i]

            if (filter_settings["type"] == "colorize"):
                colorize = FilterColorize(filter_settings, self.variables)
                img = colorize.apply(img)
            elif (filter_settings["type"] == "noise"):
                noise = FilterNoise(filter_settings, self.variables)
                img = noise.apply(img)
            elif (filter_settings["type"] == "img_overlay"):
                overlay = FilterOverlay(filter_settings, self.variables)
                img = overlay.apply(img)
            elif (filter_settings["type"] == "mask_cutout"):
                cutout = FilterCutout(filter_settings, self.variables)
                img = cutout.apply(img)
            elif (filter_settings["type"] == "hsv"):
                hsv = FilterHSV(filter_settings, self.variables)
                img = hsv.apply(img)

        img.save(path)
        img.close()

    def get_wood_name(self):
        keys = self.json_dict.keys()
        return list(keys)[0]

    def get_variables(self):
        return self.json_dict[self.wood_name]["variables"]

    def get_textures_dict(self):
        return self.json_dict[self.wood_name]["textures"]



def get_json_objects():
    data_dict = {}

    for f_name in os.listdir("json"):
        try:
            with open("json/{}".format(f_name), "r") as f:
                data = json.load(f)
                data_dict[f_name] = data
        except:
            print("Invalid json: " + f_name)

    return data_dict



json_objects = get_json_objects()

for key in json_objects.keys():
    json_object = json_objects[key]
    try:
        wood = Wood(json_object)
        wood.generate_textures("../src/trees_generated/resources/assets")
    except:
        print("Error while generating: " + key)
