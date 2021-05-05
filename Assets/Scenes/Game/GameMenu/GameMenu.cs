using UnityEngine;

public class GameMenu : MonoBehaviour
{
    [SerializeField] private GameObject playerMenuItemPrefab;

    // Start is called before the first frame update
    private void Start()
    {
        for (int i = 0; i < 6; i++)
        {
            GameObject playerMenuItem = Instantiate(playerMenuItemPrefab, playerMenuItemPrefab.transform.position, playerMenuItemPrefab.transform.rotation);

            playerMenuItem.GetComponent<Agent>().agentVariation = i + (i % 2 ==0 ? 0 : 6);
            playerMenuItem.GetComponent<Agent>().agentName = "Agent " + (i+1).ToString("000");
            playerMenuItem.name = "Agent"+i;
            playerMenuItem.transform.SetParent(gameObject.transform, false);

            RectTransform playerMenuItemRectTransform = playerMenuItem.GetComponent<RectTransform>();
            float offset = -gameObject.GetComponent<RectTransform>().rect.width / 2f;
            float menuItemWidth = playerMenuItemRectTransform.rect.width;
            float menuItemHeight = playerMenuItemRectTransform.rect.height;
            Debug.Log(menuItemWidth);


            playerMenuItemRectTransform.anchorMin = new Vector2(0, 1);
            playerMenuItemRectTransform.anchorMax = new Vector2(0, 1);
            playerMenuItem.transform.localPosition = new Vector3( offset + i * menuItemWidth, menuItemHeight, 0);
        }
    }

    // Update is called once per frame
    private void Update()
    {
    }
}