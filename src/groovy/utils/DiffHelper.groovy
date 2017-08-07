package utils
/**
 * Created by esomoza on 5/30/17.
 */
class DiffHelper {

    private List<String> diffs = []
    private String mapName = "actual"
    private String otherMapName = "expected"


    public boolean assertEquals(def actual, def expected){
        assert diff(actual, expected) == []
    }

    public List<String> diff(def actual, def expected) {
        if(actual) {
            diffEntry(null, actual, expected)
        }

        if(expected) {
            this.mapName = "expected"
            this.otherMapName = "actual"

            diffEntry(null, expected, actual)
        }

        return this.diffs
    }


    private void diffEntry (String path, Object value, Object otherValue) {
        if(!otherValue && value != otherValue) {
            this.diffs.add(this.getPropDoesntExistsMsg(path))
        } else if(value != otherValue) {
            this.diffs.add(this.getDifferentValueMsg(path, value, otherValue))
        }
    }

    private void diffEntry (String path, List list, List otherList) {
        if(!otherList && list != otherList) {
            this.diffs.add(this.getPropDoesntExistsMsg(path))
        } else {
            for (int i = 0; i < list.size(); i++) {
                diffEntry(this.createPath(path, i), list[i], otherList[i])
            }
        }
    }

    private void diffEntry (String path, Map map, Map otherMap) {
        if(!otherMap && map != otherMap) {
            this.diffs.add(this.getPropDoesntExistsMsg(path))
        } else if(map) {
            map.forEach { innerKey, innerValue ->
                if (otherMap.find { it.key == innerKey }) {
                    diffEntry(this.createPath(path, innerKey), innerValue, otherMap.get(innerKey))
                } else {
                    this.diffs.add(this.getPropDoesntExistsMsg(this.createPath(path, innerKey)))
                }
            }
        }
    }


    private String getPropDoesntExistsMsg(String path) {
        return "The " + this.otherMapName + " map doesn't have the property " + path
    }

    private String getDifferentValueMsg(String path, def value, def otherValue) {
        return "The " + this.mapName + " map has the property: " + path + " with value: " + value + " but the otherMap has the value: " + otherValue
    }

    private String createPath(String path, int index) {
        if(path) {
            return path + "[" + index + "]"
        } else {
            return "[" + index + "]"
        }
    }

    private String createPath(String path, String key) {
        if(path) {
            return path + "." + key
        } else {
            return key
        }
    }

}
